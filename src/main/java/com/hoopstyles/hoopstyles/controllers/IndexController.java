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
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
	CategoryService categoryService;

    @Autowired
	ProductService productService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if(session.getAttribute("targetURL") != null)
        {
            String targetURL = session.getAttribute("targetURL").toString();
            session.removeAttribute("targetURL");
            targetURL = targetURL.replace("http://localhost:9000", "");
            return "redirect:" + targetURL;
        }
        model.addAttribute("products", productService.all());
        return "index";
    }

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
    
    @GetMapping("/filter")
    public String filterProducts(
        @RequestParam(name="category", required=false) String categories, 
        @RequestParam(name="price", required=false) String price,
        Model model
    ) 
    {
        ArrayList<Category> categoriesList = new ArrayList<Category>();
        boolean hasFilter = false;

        if(categories != null)
        {
            for(String category : categories.split(",")) {
                categoriesList.add(categoryService.findByName(category));
            }
            model.addAttribute(
                "products", 
                productService.findByCategory(categoriesList)
            );

            hasFilter = true;
        }
        if(price != null)
        {
            List<Product> productsFiltered = new ArrayList<Product>();
            String[] prices = price.split(",");
            
            if(!categoriesList.isEmpty())
            {
                productsFiltered = productService.findByPriceBetweenAndCategory(
                    Integer.parseInt(prices[0]), 
                    Integer.parseInt(prices[1]),
                    categoriesList
                );
            } else
            {
                productsFiltered = productService.findByPriceBetween(
                    Integer.parseInt(prices[0]), 
                    Integer.parseInt(prices[1])
                );
            }
            
            model.addAttribute("products", productsFiltered);
            hasFilter = true;
        }

        return hasFilter ? "index" : "redirect:/";
    }
}
