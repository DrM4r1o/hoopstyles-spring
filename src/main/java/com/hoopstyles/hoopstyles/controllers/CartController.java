package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.services.OrderLineService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	OrderLineService orderLineService;

    @PostMapping("/add/{id}")
	public String addToCart(@PathVariable Long id, Model model, @ModelAttribute("cart") OrderLine orderLine) {
		orderLineService.insert(orderLine);
		return "redirect:/product/" + id;
	}
}
