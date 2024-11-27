package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(Model model){
         model.addAttribute("str", "Hello Spring Boot JPA Programming");
        return "index"; // index.html -> ${str}
    }

    @PostMapping("/submitData")
    @ResponseBody
    public String submitData(@RequestBody String data) {
        System.out.println(data);
        return "Data successfully submitted";
    }

    @GetMapping("/fetchTest")
    public String fetchTest() {
        //return "fetchTest";
        return "fetchTest2";
    }

    @GetMapping("/jsonPlace")
    public String jsonPlace() {
        return "jsonplace";
    }

    @GetMapping("/doc")
    public String doc() {
        return "doc";
    }
}
