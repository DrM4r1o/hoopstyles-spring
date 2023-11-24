package com.hoopstyles.hoopstyles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.services.ProductService;

@Controller
@RequestMapping("/public") //Añade /public a todos los métodos
public class PublicZoneController {

	@Autowired
	ProductService productService;
	
	// De esta forma siempre tendremos disponible los productos no vendidos. Lo incluye en el modelo
	@ModelAttribute("products")
	public List<Product> productUnsold(){
		return productService.productUnsold();
	}
	
	@GetMapping({"/","/index"})
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
		if (query != null) {
			model.addAttribute("product", productService.search(query));
		}
		return "index";
	}
	
	@GetMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable Long id) {
		Product result = productService.findById(id);
		
		if(result != null) {
			model.addAttribute("product", result);
		}
		return "redirect:/public";
	}
}
