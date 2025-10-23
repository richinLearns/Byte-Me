package ca.sheridan.byteme.bootstrap;

import ca.sheridan.byteme.beans.Role;
import ca.sheridan.byteme.beans.User;
import ca.sheridan.byteme.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("!prod") // optional: only run outside prod
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String email = "admin@cookie.com";
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("Password123!"));
            user.setRole(Role.ADMIN);
            userRepository.save(user); // Mongo will assign id
            System.out.println("Inserted demo user: " + email);
        }
    }
}