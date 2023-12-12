package com.hoopstyles.hoopstyles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.BasketballOrder;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    
    List<OrderLine> findById(long idOrder);
    OrderLine findByOrderAndProduct(BasketballOrder order, Product product);
    void delete(OrderLine ol);
    OrderLine findByProduct(Product product);

}
