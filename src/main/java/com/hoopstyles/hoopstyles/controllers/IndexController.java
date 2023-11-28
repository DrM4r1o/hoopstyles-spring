package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
public class IndexController {

    @Autowired
	CategoryService categoryService;

    @Autowired
	UserService userService;
    
    @GetMapping("/")
	public String index(Model model) {
        if(categoryService.all().size() > 0)
        {
            model.addAttribute("categories", categoryService.all());
        }
        model.addAttribute("users", userService.all());
		return "index";
	}
}
