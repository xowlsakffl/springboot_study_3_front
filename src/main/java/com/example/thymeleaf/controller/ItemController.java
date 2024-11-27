package com.example.thymeleaf.controller;

import com.example.thymeleaf.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("apple", 10));
        items.add(new Item("orange", 20));
        items.add(new Item("pear", 30));
        model.addAttribute("items", items);
        return "items";
    }
}
