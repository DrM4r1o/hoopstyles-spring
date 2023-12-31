package com.hoopstyles.hoopstyles.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.Address;
import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.InfoProfileUpdated;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.AddressService;
import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.OrderService;
import com.hoopstyles.hoopstyles.services.ProductService;
import com.hoopstyles.hoopstyles.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categortService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public String profile(Model model, HttpServletRequest request, HttpSession session) {
        if(!userService.userIsAuthenticated()) {
            String targetURL = request.getRequestURL().toString();
            session.setAttribute("targetURL", targetURL);
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        model.addAttribute("user", userHoop);
        return "profile/profile";
    }

    @GetMapping("")
    public String redirectProfile() {
        return "redirect:/profile/";
    }

    @GetMapping("/info")
    public String info(Model model) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }
        
        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        model.addAttribute("infoProfileUpdated", 
            new InfoProfileUpdated(
                userHoop.getName(),
                userHoop.getSurname(),
                userHoop.getEmail(),
                null
        ));
        return "profile/information";
    }

    @PostMapping("/info/edit")
    public String edit(@ModelAttribute("infoProfileUpdated") InfoProfileUpdated infoProfileUpdated) {

        String name = infoProfileUpdated.getName();
        String surname = infoProfileUpdated.getSurname();
        String email = infoProfileUpdated.getEmail();
        String newPassword = infoProfileUpdated.getNewPassword();

        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        if(name == null || surname == null || email == null) {
            return "redirect:/profile/info";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        userHoop.setName(name);
        userHoop.setSurname(surname);
        
        if(newPassword != null && newPassword != "") {
            userHoop.setPassword(newPassword);
            userService.register(userHoop);
        }
        
        if(!userHoop.getEmail().equals(email))
        {
            userHoop.setEmail(email);
            userService.save(userHoop);
            return "redirect:/auth/logout";
        }
        
        userService.save(userHoop);
        return "redirect:/profile/info";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        if(!userHoop.getRole().equals("ADMIN")) {
            return "redirect:/profile/";
        }

        int sales = 0;

        for(BasketballOrder order : orderService.all()) {
            if(!order.isActive()) {
                sales += order.getTotal();
            }
        }


        model.addAttribute("sales", sales);
        model.addAttribute("users", userService.all());
        model.addAttribute("orders", orderService.all());
        model.addAttribute("products", productService.all());
        model.addAttribute("categories", categortService.all());
        
        return "profile/admin";
    }

    @GetMapping("/addresses")
    public String addresses(Model model) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());

        model.addAttribute("newAddress", new Address());
        model.addAttribute("addresses", userHoop.getAddresses());

        return "profile/addresses";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        List<BasketballOrder> orders = orderService.byOwnerAndState(userHoop, false);

        model.addAttribute("orders", orders);
        model.addAttribute("addressService", addressService);

        return "profile/orders";
    }
    

    @ModelAttribute("user")
	public String usuario() {
		return userService.getUsername();
	}

    @ModelAttribute("role")
	public String role() {
        if(!userService.userIsAuthenticated()) {
            return "GUEST";
        }
		return userService.findByEmail(userService.getUsername()).getRole();
	}

    @ModelAttribute("cartCount")
    public int cartCount() {
        UserHoop user = userService.findByEmail(userService.getUsername());
        if(user == null) {
            return 0;
        }
        BasketballOrder order = orderService.getActiveOrder(user);
        return order.getOrderLines().size();
    }

}

