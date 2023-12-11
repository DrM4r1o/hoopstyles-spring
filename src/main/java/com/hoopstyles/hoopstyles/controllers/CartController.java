package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.OrderLine;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.OrderLineService;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderLineService orderLineService;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String cart(Model model) {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return "redirect:/auth/login";
        }
        BasketballOrder order = orderService.getActiveOrder(user);
        model.addAttribute("order", order);
        return "cart";
    }

    @GetMapping("")
    public String redirectProfile() {
        return "redirect:/cart/";
    }
    
    @PostMapping("/add/{id}")
	public String addToCart(@PathVariable Long id, Model model) {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return "redirect:/auth/login";
        }

        Product product = productService.findById(id);
        BasketballOrder order = orderService.getActiveOrder(user);
        OrderLine orderLine = orderLineService.saveOrderLine(order, product);

        order.addOrderLine(orderLine);
        orderService.save(order);
		return "redirect:/product/" + id;
	}

    @ModelAttribute("user")
	public String usuario(Model model) {
		return userService.getUsername();
	}

    @ModelAttribute("cartCount")
    public int cartCount(Model model) {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return 0;
        }
        BasketballOrder order = orderService.getActiveOrder(user);
        return order.getOrderLines().size();
    }
}
