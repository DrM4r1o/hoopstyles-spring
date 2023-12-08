package com.hoopstyles.hoopstyles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.model.BasketballOrder;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    
    List<OrderLine> findByOrder(long idOrder);

}
