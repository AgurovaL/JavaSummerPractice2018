package com.agurova.controllers;

import com.agurova.dal.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private UserRepository userRepository;

    @GetMapping("/users")
    public String usersGet() {
        return "users";
    }

    public void usersPost() {
    }
}