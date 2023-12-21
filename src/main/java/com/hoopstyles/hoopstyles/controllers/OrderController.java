package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoopstyles.hoopstyles.model.Address;
import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.AddressService;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;

    @Autowired
    AddressService addressService;
	
	@Autowired
	HttpSession session;

    @PostMapping("/finish")
    public String finish(@RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         @RequestParam("email") String email,
                         @RequestParam("address") long address) 
    {
        if(!userService.userIsAuthenticated())
        {
            return "redirect:/auth/login";
        }
        if(name == null || email == null || surname == null)
        {
            return "redirect:/cart/";
        }

        UserHoop user = userService.findByEmail(userService.getUsername());
        BasketballOrder order = orderService.getActiveOrder(user);

        user.setSurname(surname);
        order.setState(false);
        order.setAddressId(address);

        orderService.save(order);
        userService.save(user);

        return "redirect:/";
    }
}
