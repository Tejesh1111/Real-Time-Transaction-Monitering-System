package com.bank;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // maps the root URL (http://localhost:8081/)
    public String home() {
        return "home"; // refers to home.html in src/main/resources/templates/
    }
}
