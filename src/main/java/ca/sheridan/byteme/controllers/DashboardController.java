package ca.sheridan.byteme.controllers;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridan.byteme.beans.User;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal) {

        // --- 1. Dynamic Clock Logic (Restored) ---
        // Define the target time zone: America/Toronto (EST/EDT)
        ZoneId userZone = ZoneId.of("America/Toronto"); // Default to Toronto

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User currentUser) {
            if (currentUser.getTimezone() != null && !currentUser.getTimezone().isEmpty()) {
                userZone = ZoneId.of(currentUser.getTimezone());
            }
        }
        
        // Get and format the current time in that time zone
        ZonedDateTime zonedTime = ZonedDateTime.now(userZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        String formattedTime = zonedTime.format(formatter);

        // Add the formatted time string to the model for Thymeleaf (for initial load)
        model.addAttribute("currentTime", formattedTime);

        // --- 2. User/Security Details ---
        String username = (principal != null) ? principal.getName() : "Guest";
        model.addAttribute("username", username);
        
        // 3. Return the name of the Thymeleaf template
       return "dashboard"; 
    }
}