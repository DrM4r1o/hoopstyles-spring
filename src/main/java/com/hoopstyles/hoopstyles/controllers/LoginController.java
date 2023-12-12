package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.UserService;
import com.hoopstyles.hoopstyles.services.CategoryService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

    @Autowired
	CategoryService categoryService;
	
	@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name="error", required=false) String errorMessage) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof User) {
            return "redirect:/";
        }

        if(errorMessage != null) {
            model.addAttribute("errorMessage", "User or password incorrect");
        }
        model.addAttribute("newUser", new UserHoop());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute UserHoop user) {
        String email = user.getEmail();
        String emailAdmin = "admin@hoopstyles.com";
        if(email.equals(emailAdmin)) {
            user.setRole("ADMIN");
        }

		userService.register(user);
		return "redirect:/auth/login";
	}
}
