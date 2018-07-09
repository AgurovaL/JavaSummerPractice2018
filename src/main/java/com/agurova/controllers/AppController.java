package com.agurova.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/hello")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello!");
        return "hello";
    }


}