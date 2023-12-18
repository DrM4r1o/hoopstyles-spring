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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


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
	public String addToCart(@PathVariable Long id, Model model, HttpServletRequest request) {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return "redirect:/auth/login";
        }

        Product product = productService.findById(id);
        BasketballOrder order = orderService.getActiveOrder(user);
        OrderLine orderLine = orderLineService.findByProduct(product) == null
                            ? orderLineService.saveOrderLine(order, product)
                            : orderLineService.findByProduct(product);

        if(order.alreadyAdded(orderLine)) {
            orderLine.setQuantity(orderLine.getQuantity() + 1);
            orderLineService.save(orderLine);
        } else {
            order.addOrderLine(orderLine);
        }

        orderService.save(order);

        String originURL = request.getHeader("referer");

		return "redirect:" + originURL;
	}

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, Model model) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop user = userService.findByEmail(userService.getUsername());
        Product product = productService.findById(id);
        BasketballOrder order = orderService.getActiveOrder(user);
        OrderLine orderLine = orderLineService.findByOrderAndProduct(order, product);

        orderLineService.delete(orderLine);

        return "redirect:/cart";
    }

    @PostMapping("/reduce/{id}")
    public String reduceQuantity(@PathVariable Long id) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop user = userService.findByEmail(userService.getUsername());
        Product product = productService.findById(id);
        BasketballOrder order = orderService.getActiveOrder(user);
        OrderLine orderLine = orderLineService.findByOrderAndProduct(order, product);

        if(orderLine.getQuantity() == 1) {
            orderLineService.delete(orderLine);
            return "redirect:/cart";
        }

        orderLine.setQuantity(orderLine.getQuantity() - 1);
        orderLineService.save(orderLine);

        return "redirect:/cart";
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
