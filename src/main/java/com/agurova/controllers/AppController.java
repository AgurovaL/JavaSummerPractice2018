package com.agurova.controllers;

import com.agurova.models.User;
import com.agurova.services.user.dal.impl.UserRepositoryServiceImpl;
import com.agurova.services.user.helper.impl.UserMainServiceImpl;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Data
public class AppController {
    private static final Logger LOG = Logger.getLogger(AppController.class);
    private UserMainServiceImpl service;

    @GetMapping("/welcome")
    public String welcomePageShow(Model model) {
        model.addAttribute("message", "This is welcome page!");
        return "welcome";
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

        model.addAttribute("user", new User());
//        model.addAttribute("login", login);
//        model.addAttribute("password", password);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute User user) {
        String role = service.authorize(user.getLogin(), user.getPassword());
        if (role.equals("USER")) {
            return "redirect:/user/" + user.getUserId();
        }
        if (role.equals("ADMIN")){
            return "redirect:/admin";
        }
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute User user) {
        service.save(user);
        return "redirect:/user/" + user.getUserId();
    }

    @GetMapping("/admin")
    public String adminPageGet(Model model) {
        model.addAttribute("message", "This is protected page!");
        return "admin";
    }

    @GetMapping("/user/{id}")
    public String userGet(Model model, @PathVariable("userId") String id) {
        model.addAttribute("id", id);
        return "user";
    }

}
