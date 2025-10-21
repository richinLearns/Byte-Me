package ca.sheridan.byteme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            // Disables CSRF protection for the H2 console only (required by H2)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(request -> request.getRequestURI().startsWith("/h2-console/"))
            )
            
            // Authorization settings
            .authorizeHttpRequests(authorize -> authorize
                // Allow anyone to access the H2 console
                .requestMatchers("/h2-console/**").permitAll()
                // Allow anyone to access the dashboard for now (you'll restrict this later)
                .requestMatchers("/", "/dashboard", "/css/**", "/js/**").permitAll()
                // Require authentication for all other requests
                .anyRequest().authenticated()
            )
            
            // Setup for the H2 console frame (also required by H2)
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                    .sameOrigin()
                )
            );

        return http.build();
    }
}
