package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.UserService;
import com.hoopstyles.hoopstyles.services.CategoryService;


@Controller
public class LoginController {

	@Autowired
	UserService userService;

    @Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public String welcome(@ModelAttribute Model model) {
        model.addAttribute("categories", categoryService.all());
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name="errorMessage", required=false) String errorMessage) {
		if(errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("newUser", new UserHoop());
		return "login";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute UserHoop user) {
		userService.register(user);
        System.out.println("Usuario registrado: " + user);
		return "redirect:/login"; //Para pasar a la página de inicio del usuario hay que investigar más sobre Spring Security
	}

    @PostMapping("/login-post")
    public String loginPost(@ModelAttribute UserHoop user, RedirectAttributes ra) {
        UserHoop userFound = userService.findByEmail(user.getEmail());
        if (userFound == null) {
            ra.addAttribute("errorMessage", "User or password incorrect");
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
