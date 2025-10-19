package ca.sheridan.byteme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // src/main/resources/templates/index.html (already present)
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // will render src/main/resources/templates/login.html
    }
}