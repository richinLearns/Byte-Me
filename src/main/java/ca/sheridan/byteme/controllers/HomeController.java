package ca.sheridan.byteme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(params = "logout")
    public String indexWithLogout(org.springframework.ui.Model model) {
        model.addAttribute("logoutSuccess", true);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // will render src/main/resources/templates/login.html
    }
}