package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @PostMapping("/remove/{id}")
    public String removeCategory(@PathVariable Long id) {
        Category c = categoryService.findById(id);
        categoryService.delete(c);
        return "redirect:/profile/admin";
    }


}
