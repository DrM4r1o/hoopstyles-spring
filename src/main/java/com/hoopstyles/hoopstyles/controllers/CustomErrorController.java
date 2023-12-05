package com.hoopstyles.hoopstyles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class CustomErrorController implements ErrorController {
	
	@RequestMapping("/error")
	public String error() {
		return "404";
	}
	
}