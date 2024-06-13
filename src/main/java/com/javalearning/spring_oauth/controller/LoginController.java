package com.javalearning.spring_oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/api/auth/login")
    public String loginPage() {
        return "login"; // Ensure this maps to login.html in src/main/resources/templates
    }

    @GetMapping("/api/auth/home")
    public String home() {
        return "home"; // Ensure this maps to home.html in src/main/resources/templates
    }
}
