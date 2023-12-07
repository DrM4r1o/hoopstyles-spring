package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String profile(Model model) {
        String name = getUsername();

        if(name == null) {
            return "redirect:/auth/login";
        }

        UserHoop userHoop = userService.findByEmail(getUsername());
        model.addAttribute("user", userHoop);
        return "profile/profile";
    }

    @GetMapping("")
    public String redirectProfile() {
        return "redirect:/profile/";
    }

    @GetMapping("/info")
    public String info(Model model) {
        if(!userIsAuthenticated()) {
            return "redirect:/auth/login";
        }
        
        UserHoop userHoop = userService.findByEmail(getUsername());
        model.addAttribute("user", userHoop);
        return "profile/information";
    }

    @PostMapping("/info/edit")
    public String edit(@RequestParam("name") String name, 
                       @RequestParam("surname") String surname,
                       @RequestParam("email") String email,
                       @RequestParam("newPassword") String newPassword
    ) {
        if(!userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        if(name == null || surname == null || email == null) {
            return "redirect:/profile/info";
        }

        UserHoop userHoop = userService.findByEmail(getUsername());
        userHoop.setName(name);
        userHoop.setSurname(surname);
        if(userHoop.getEmail() != email)
        {
            userHoop.setEmail(email);
            return "redirect:/auth/logout";
        }

        if(newPassword != null && newPassword != "") {
            userHoop.setPassword(newPassword);
        }

        userService.save(userHoop);
        return "redirect:/profile/info";
    }

    private boolean userIsAuthenticated() {
        String name = getUsername();
        
        if(name == null) {
            return false;
        }

        return true;
    }

    private String getUsername() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = user.getUsername();
            return name;
        } catch(Exception e) {
            return null;
        }
    }
}

