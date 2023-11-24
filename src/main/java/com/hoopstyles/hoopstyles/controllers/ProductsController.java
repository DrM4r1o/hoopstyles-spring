package com.hoopstyles.hoopstyles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
@RequestMapping("/app")
public class ProductsController {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;
	
	//@Autowired
	//StorageService storageService;
	
	private UserHoop user;

	@ModelAttribute("myproducts")
	public List<Product> myProducts() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.buscarPorEmail(email);
		return productService.productsOfOwner(user);
	}

	@GetMapping("/myproducts")
	public String list(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null)
			model.addAttribute("myproducts", productService.searchMyProducts(query, user));

		return "app/producto/lista";
	}
	
	@GetMapping("/myproducts/{id}/delete")
	public String delete(@PathVariable Long id) {
		Product p = productService.findById(id);
		if (p.getOrder() == null)
			productService.delete(p);
		return "redirect:/app/myproducts";
	}
	
	@GetMapping("/product/new")
	public String newProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "app/product/form";
	}
	
	@PostMapping("/product/nuevo/submit")
	public String newProductSubmit(@ModelAttribute Product product, @RequestParam("file") MultipartFile file) {	
		/*
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			producto.setImagen(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", imagen).build().toUriString());
		}
		*/
		product.setPropietario(user);
		productService.insert(product);
		return "redirect:/app/myproducts";
	}
	
}
