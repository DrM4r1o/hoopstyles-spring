package com.hoopstyles.hoopstyles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.repository.OrderLineRepository;

@Service
public class OrderLineService {

    @Autowired
    OrderLineRepository repository;

    public OrderLine insert(OrderLine ol) {
        return repository.save(ol);
    }

    public List<OrderLine> findByOrder(long idOrder) {
        return repository.findByOrder(idOrder);
    }
    
    
}
