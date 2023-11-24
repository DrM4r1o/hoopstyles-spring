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

import com.hoopstyles.hoopstyles.model.Order;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class CompraController {

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
	
	@ModelAttribute("carrito")
	public List<Product> productosCarrito() {
		List<Long> contenido = (List<Long>) session.getAttribute("cart");
		return (contenido == null) ? null : productService.variousById(contenido);
	}
	
	@ModelAttribute("total_carrito")
	public Double totalCarrito() {
		List<Product> productosCarrito = productosCarrito();
		
		if(productosCarrito != null) {
			return productosCarrito.stream().mapToDouble(p -> p.getPrecio()).sum();
		}
		return 0.0;
	}
	
	@ModelAttribute("mis_compras")
	public List<Order> misCompras() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName(); //obtenemos el email
		user = userService.buscarPorEmail(email);
		return orderService.porPropietario(user);
	}
	
	@GetMapping("/carrito")
	public String verCarrito(Model model) {
		return "app/compra/carrito";
	}
	
	@GetMapping("/carrito/add/{id}")
	public String addCarrito(Model model, @PathVariable Long id) {
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		
		if(contenido == null)
			contenido = new ArrayList<>();
		
		if(!contenido.contains(id))
			contenido.add(id);
		
		session.setAttribute("carrito", contenido);
		
		return "redirect:/app/carrito";
	}
	
	@GetMapping("/carrito/eliminar/{id}")
	public String borrarDeCarrito(Model model, @PathVariable Long id) {
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		
		if(contenido == null)
			return "redirect:/public";
		
		contenido.remove(id);
		
		if(contenido.isEmpty())
			session.removeAttribute("carrito");
		else
			session.setAttribute("carrito", contenido);
		
		return "redirect:/app/carrito";
	}
	
	@GetMapping("/carrito/finalizar")
	public String checkout() {
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		
		if(contenido == null)
			return "redirect:/public";
		
		List<Product> productos = productosCarrito();
		
		Compra c = orderService.insertar(new Compra(), user);
		productos.forEach(p -> orderService.addProductoCompra(p, c));
		session.removeAttribute("carrito");
		
		return "redirect:/app/compra/factura/"+c.getId();
	}
	
	@GetMapping("/compra/factura/{id}")
	public String factura(Model model, @PathVariable Long id) {
		Compra c = orderService.buscarPorId(id);
		List<Product> productos = productService.productosDeUnaCompra(c);
		model.addAttribute("productos", productos);
		model.addAttribute("compra",c);
		model.addAttribute("total_compra", productos.stream().mapToDouble(p -> p.getPrecio()).sum());
		return "app/compra/factura";
	}
	
	@GetMapping("/miscompras")
	public String verMisCompras(Model model) {
		return "/app/compra/listado";
	}
	
	
	
}
