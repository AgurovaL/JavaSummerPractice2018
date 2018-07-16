package com.agurova.controllers;

import com.agurova.models.User;
import com.agurova.services.user.dal.impl.UserRepositoryServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private static final Logger LOG = Logger.getLogger(AppController.class);
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
        return "redirect:/user-page/"; //+ user.getId();
    }

    @GetMapping("/admin")
    public String adminPageGet(Model model) {
        model.addAttribute("message", "This is protected page!");
        return "admin";

    }
}
