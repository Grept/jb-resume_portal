package com.grept.resumeportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello";
    }

    @GetMapping("/edit")
    public String edit() {
        return "Edit Page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        model.addAttribute("userId", userId);
        return "profile-templates/2/index";
    }
}
