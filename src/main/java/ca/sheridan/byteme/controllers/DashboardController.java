package ca.sheridan.byteme.controllers;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal) {

        // --- 1. Dynamic Clock Logic (Restored) ---
        // Define the target time zone: America/Toronto (EST/EDT)
        ZoneId torontoZone = ZoneId.of("America/Toronto");
        
        // Get and format the current time in that time zone
        ZonedDateTime torontoTime = ZonedDateTime.now(torontoZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        String formattedTime = torontoTime.format(formatter);

        // Add the formatted time string to the model for Thymeleaf (for initial load)
        model.addAttribute("currentTime", formattedTime);

        // --- 2. User/Security Details ---
        String username = (principal != null) ? principal.getName() : "Guest";
        model.addAttribute("username", username);
        
        // 3. Return the name of the Thymeleaf template
       return "dashboard"; 
    }
}