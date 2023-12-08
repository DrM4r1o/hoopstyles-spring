package com.hoopstyles.hoopstyles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoopstyles.hoopstyles.model.InfoProfileUpdated;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String profile(Model model, HttpServletRequest request, HttpSession session) {
        if(!userIsAuthenticated()) {
            String targetURL = request.getRequestURL().toString();
            session.setAttribute("targetURL", targetURL);
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
        model.addAttribute("infoProfileUpdated", new InfoProfileUpdated(
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

        if(!userIsAuthenticated()) {
            return "redirect:/auth/login";
        }

        if(name == null || surname == null || email == null) {
            return "redirect:/profile/info";
        }

        UserHoop userHoop = userService.findByEmail(getUsername());
        userHoop.setName(name);
        userHoop.setSurname(surname);
        
        if(newPassword != null && newPassword != "") {
            userHoop.setPassword(newPassword);
        }
        
        if(userHoop.getEmail() != email)
        {
            userHoop.setEmail(email);
            userService.save(userHoop);
            return "redirect:/auth/logout";
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

