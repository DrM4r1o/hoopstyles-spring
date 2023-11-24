package com.hoopstyles.hoopstyles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.Order;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	@Autowired
	ProductService productService;
	
	public Order insertar(Order c, UserHoop u) {
		c.setPropietario(u);
		return repository.save(c);
	}
	
	public Order insertar(Order c) {
		return repository.save(c);
	}
	
	public Product addProductoOrder(Product p, Order c) {
		p.setOrder(c);
		return productService.editar(p);
	}
	
	public Order buscarPorId(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Order> todas() {
		return repository.findAll();
	}
	
	public List<Order> porPropietario(UserHoop u) {
		return repository.findByOwner(u);
	}
}
