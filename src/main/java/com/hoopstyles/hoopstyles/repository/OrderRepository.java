package com.hoopstyles.hoopstyles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.Order;
import com.hoopstyles.hoopstyles.model.UserHoop;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByOwner(UserHoop owner);
}

