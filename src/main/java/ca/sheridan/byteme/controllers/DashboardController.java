package ca.sheridan.byteme.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// THIS IS TEMPORARY HAHA FOR DEMO PURPOSES ONLY
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(Principal principal) {
        String username = (principal != null) ? principal.getName() : "unknown";
        String html = "<!doctype html><html><head><meta charset='utf-8'><title>Dashboard</title>"
            + "<style>body{font-family:Arial,sans-serif;padding:2rem;background:#f5f5f5} .card{background:#fff;padding:1.5rem;border-radius:8px;max-width:600px;margin:0 auto;}</style>"
            + "</head><body><div class='card'><h1>Login successful</h1>"
            + "<p>Signed in as: <strong>" + username + "</strong></p>"
            + "<p><a href='/'>Go to homepage</a> · <a href='/logout'>Log out</a></p>"
            + "</div></body></html>";
        return ResponseEntity.ok().body(html);
    }
}