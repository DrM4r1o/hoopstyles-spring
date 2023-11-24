package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new UserHoop()); //Le pasamos el UserHoop por si hacemos un registro
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute UserHoop usuario) {
		userService.registrar(usuario);
		return "redirect:/auth/login"; //Para pasar a la página de inicio del usuario hay que investigar más sobre Spring Security
	}
}
