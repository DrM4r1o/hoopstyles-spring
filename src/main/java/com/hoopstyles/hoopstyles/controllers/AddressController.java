package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoopstyles.hoopstyles.model.Address;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.AddressService;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public String addAddress(@ModelAttribute("newAddress") Address newAddress) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        newAddress.setUser(userHoop);
        userHoop.addAddress(newAddress);

        addressService.insert(newAddress);
        userService.save(userHoop);

        return "redirect:/profile/addresses";
    }

    @PostMapping("/remove/{id}")
    public String addAddress(@PathVariable("id") long id) {
        if(!userService.userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(userService.getUsername());
        Address address = addressService.searchById(id);

        userHoop.removeAddress(address);

        addressService.remove(address);
        userService.save(userHoop);

        return "redirect:/profile/addresses";
    }

}

