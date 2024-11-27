package com.example.sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(Model model){
         model.addAttribute("str", "Hello Spring Boot JPA Programming");
        return "index"; // index.html -> ${str}
    }
}
