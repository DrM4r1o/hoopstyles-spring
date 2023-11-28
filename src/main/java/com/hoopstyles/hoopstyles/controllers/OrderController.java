package com.hoopstyles.hoopstyles.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	//Esta clase controla la sesión
	@Autowired
	HttpSession session;
	
	private UserHoop user;
	
	@ModelAttribute("cart")
	public List<Product> productsCart() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		return (content == null) ? null : productService.variousById(content);
	}
	
	@ModelAttribute("totalCart")
	public Double totalCart() {
		List<Product> productsCart = productsCart();
		
		if(productsCart != null) {
			return productsCart.stream().mapToDouble(p -> p.getPrice()).sum();
		}
		return 0.0;
	}
	
	@ModelAttribute("myOrders")
	public List<BasketballOrder> myOrders() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName(); //obtenemos el email
		user = userService.findByEmail(email);
		return orderService.byOwner(user);
	}
	
	@GetMapping("/cart")
	public String seeCart(Model model) {
		return "app/Order/cart";
	}
	
	@GetMapping("/cart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		
		if(content == null)
			content = new ArrayList<>();
		
		if(!content.contains(id))
			content.add(id);
		
		session.setAttribute("cart", content);
		
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/delete/{id}")
	public String deleteFromCart(Model model, @PathVariable Long id) {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		
		if(content == null)
			return "redirect:/public";
		
		content.remove(id);
		
		if(content.isEmpty())
			session.removeAttribute("cart");
		else
			session.setAttribute("cart", content);
		
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/end")
	public String checkout() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		
		if(content == null)
			return "redirect:/public";
		
		List<Product> products = productsCart();
		
		BasketballOrder c = orderService.insert(new BasketballOrder(), user);
		products.forEach(p -> orderService.addProductoOrder(p, c));
		session.removeAttribute("cart");
		
		return "redirect:/app/Order/bill/"+c.getId();
	}
	
	@GetMapping("/Order/bill/{id}")
	public String bill(Model model, @PathVariable Long id) {
		BasketballOrder c = orderService.searchById(id);
		List<Product> products = productService.productsOfOrder(c);
		model.addAttribute("products", products);
		model.addAttribute("Order",c);
		model.addAttribute("total_Order", products.stream().mapToDouble(p -> p.getPrice()).sum());
		return "app/Order/bill";
	}
	
	@GetMapping("/myOrders")
	public String seeMyOrders(Model model) {
		return "/app/Order/listado";
	}
	
	
	
}
