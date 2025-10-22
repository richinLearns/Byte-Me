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
            User user = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode("Password123!"))
                    .role(Role.ADMIN) // adjust role enum as needed
                    .build();
            userRepository.save(user);
            System.out.println("Inserted test user: " + email + " / Password123!");
        }

        String staffEmail = "staff@cookie.com";
        if (userRepository.findByEmail(staffEmail).isEmpty()) {
            User staff = User.builder()
                    .email(staffEmail)
                    .password(passwordEncoder.encode("Password123!"))
                    .role(Role.STAFF)
                    .build();
            userRepository.save(staff);
            System.out.println("Inserted test user: " + staffEmail + " / Password123!");
        }

        String customerEmail = "customer@cookie.com";
        if (userRepository.findByEmail(customerEmail).isEmpty()) {
            User customer = User.builder()
                    .email(customerEmail)
                    .password(passwordEncoder.encode("Password123!"))
                    .role(Role.CUSTOMER)
                    .build();
            userRepository.save(customer);
            System.out.println("Inserted test user: " + customerEmail + " / Password123!");
        }
    }
}