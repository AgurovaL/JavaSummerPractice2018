package com.agurova.controllers;

import com.agurova.models.User;
import com.agurova.services.user.dal.impl.UserRepositoryServiceImpl;
import com.agurova.services.user.helper.impl.UserMainServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private static final Logger LOG = Logger.getLogger(AppController.class);
    UserMainServiceImpl service;

    @GetMapping("/welcome")
    public String welcomePageGet(Model model) {
        model.addAttribute("message", "This is welcome page!");
        return "welcome";
    }

    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("client", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute User user) {
        new UserRepositoryServiceImpl().save(user);
        return "redirect:/images/favoriteImages";
    }

    @GetMapping("/admin")
    public String adminPageGet(Model model) {
        model.addAttribute("message", "This is protected page!");
        return "admin";

    }

    @GetMapping("/login")
    public String loginGet(Model model,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        String login = "", password= "";
        model.addAttribute("login", login);
        model.addAttribute("password", password);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute String login, @ModelAttribute String password) {
        String role = service.authorize(login, password);
        if (role.equals("USER")) {
            return "redirect:/images/favoriteImages";
        }
        if (role.equals("ADMIN")){
            return "redirect:/admin";
        }
        return "redirect:/login";
    }


}
