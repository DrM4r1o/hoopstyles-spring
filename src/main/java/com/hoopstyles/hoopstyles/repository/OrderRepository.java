package com.hoopstyles.hoopstyles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.UserHoop;

public interface OrderRepository extends JpaRepository<BasketballOrder, Long> {
	
	List<BasketballOrder> findByOwner(UserHoop owner);
	void insert(BasketballOrder c);
}

