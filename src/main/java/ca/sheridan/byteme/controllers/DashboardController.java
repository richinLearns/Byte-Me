package ca.sheridan.byteme.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping({"/", "/dashboard"}) // Maps to both the root and /dashboard URL
    public String getDashboard(Model model) {

        // 1. Define the target time zone: America/Toronto (EST/EDT)
        ZoneId torontoZone = ZoneId.of("America/Toronto");

        // 2. Get the current time in that time zone
        ZonedDateTime torontoTime = ZonedDateTime.now(torontoZone);

        // 3. Define the desired time format (e.g., 7:03 PM)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        // 4. Format the time as a String
        String formattedTime = torontoTime.format(formatter);

        // 5. Add the formatted time string to the model
        // This is what Thymeleaf picks up with ${currentTime}
        model.addAttribute("currentTime", formattedTime);

        // 6. Return the name of the Thymeleaf template
        return "dashboard"; // Corresponds to src/main/resources/templates/dashboard.html
    }
}