package com.hoopstyles.hoopstyles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.UserService;

import ch.qos.logback.core.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class CustomErrorController implements ErrorController {
	
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

	@RequestMapping("/error")
	public String error() {
		return "404";
	}

    @ModelAttribute("cartCount")
    public int cartCount(Model model) {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return 0;
        }
        BasketballOrder order = orderService.getActiveOrder(user);
        return order.getOrderLines() != null 
                ? order.getOrderLines().size() 
                : 0;
    }
}
