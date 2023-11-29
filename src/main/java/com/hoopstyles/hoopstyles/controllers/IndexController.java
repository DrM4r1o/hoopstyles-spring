package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.ProductService;

@Controller
public class IndexController {

    @Autowired
	CategoryService categoryService;

    @Autowired
	ProductService productService;
    
    @GetMapping("/")
	public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = null;

        if(principal instanceof User) {
            User user = (User) principal;
            name = user.getUsername();
        }

        model.addAttribute("categories", categoryService.all());
        model.addAttribute("products", productService.all());
        model.addAttribute("user", name);
		return "index";
	}

    @GetMapping("/products/filter")
    public String filterProducts(@RequestParam(name="category", required=false) String categories, Model model) {
        // model.addAttribute("products", productService.filter(categories));
        return "index";
    }
}
