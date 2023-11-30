package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

import com.hoopstyles.hoopstyles.model.Category;

import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.ProductService;

@Controller
public class IndexController {

    @Autowired
	CategoryService categoryService;

    @Autowired
	ProductService productService;

    @ModelAttribute("user")
	public String usuario(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = null;

        if(principal instanceof User) {
            User user = (User) principal;
            name = user.getUsername();
        }

		return name;
	}

    @ModelAttribute("categories")
    public List<Category> categories(Model model) {
        return categoryService.all();
    }
    
    @GetMapping("/")
	public String index(Model model) {
        model.addAttribute("products", productService.all());
		return "index";
	}

    @GetMapping("/filter")
    public String filterProducts(
        @RequestParam(name="category", required=false) String categories, 
        @RequestParam(name="price", required=false) String price,
        Model model
    ) 
    {
        if(categories != null)
        {
            ArrayList<Category> categoriesList = new ArrayList<Category>();
            for(String category : categories.split(",")) {
                categoriesList.add(categoryService.findByName(category));
            }
            model.addAttribute(
                "products", 
                productService.findByCategory(categoriesList)
            );
        }
        if(price != null)
        {
            String[] prices = price.split(",");
            model.addAttribute("products", 
                productService.findByPriceBetween(
                    Integer.parseInt(prices[0]), 
                    Integer.parseInt(prices[1])
                )
            );
        }
        return "index";
    }
}
