package com.hoopstyles.hoopstyles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	@Autowired
	ProductService productService;
	
	public BasketballOrder insert(BasketballOrder c, UserHoop u) {
		c.setOwner(u);
		return repository.save(c);
	}
	
	public BasketballOrder insert(BasketballOrder c) {
		return repository.save(c);
	}
	
	public Product addProductoOrder(Product p, BasketballOrder c) {
		p.setOrder(c);
		return productService.editar(p);
	}
	
	public BasketballOrder searchById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<BasketballOrder> all() {
		return repository.findAll();
	}
	
	public List<BasketballOrder> byOwner(UserHoop u) {
		return repository.findByOwner(u);
	}
}
