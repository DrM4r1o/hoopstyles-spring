package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
public class ProductsController {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;
	
	private UserHoop user;
	
	@GetMapping("/product/{id}")
	public String productPage(@PathVariable Long id, Model model) {
		Product p = productService.findById(id);
        model.addAttribute("product", p);
		return "product";
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
		product.setOwner(user);
		productService.insert(product);
		return "redirect:/app/myproducts";
	}

	    private boolean userIsAuthenticated() {
        String name = getUsername();
        
        if(name == null) {
            return false;
        }

        return true;
    }

    private String getUsername() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = user.getUsername();
            return name;
        } catch(Exception e) {
            return null;
        }
    }
	
}
