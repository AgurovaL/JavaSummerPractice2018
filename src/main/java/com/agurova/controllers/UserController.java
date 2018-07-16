package com.agurova.controllers;

import com.agurova.services.user.helper.impl.UserMainServiceImpl;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
@Data
public class UserController {
    private static final Logger LOG = Logger.getLogger(ImageController.class);
    UserMainServiceImpl service;

    @GetMapping("/all")
    public String usersGet(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "allUsers";
    }

}