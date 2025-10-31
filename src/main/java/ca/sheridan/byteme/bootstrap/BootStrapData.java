package ca.sheridan.byteme.bootstrap;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ca.sheridan.byteme.beans.Promotion;
import ca.sheridan.byteme.beans.Role;
import ca.sheridan.byteme.beans.User;
import ca.sheridan.byteme.repositories.UserRepository;
import ca.sheridan.byteme.services.PromotionService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Profile("!prod") // optional: only run outside prod
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PromotionService promotionService;   // <-- add this

    @Override
    public void run(String... args) throws Exception {
        // --- existing demo users (unchanged) ---
        String adminEmail = "admin@cookie.com";
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = User.builder()
                    .name("Admin User")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("Password123!"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Inserted demo user: " + adminEmail + " / Password123!");
        }

        String staffEmail = "staff@cookie.com";
        if (userRepository.findByEmail(staffEmail).isEmpty()) {
            User staff = User.builder()
                    .name("Staff User")
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
                    .name("Customer User")
                    .email(customerEmail)
                    .password(passwordEncoder.encode("Password123!"))
                    .role(Role.CUSTOMER)
                    .build();
            userRepository.save(customer);
            System.out.println("Inserted test user: " + customerEmail + " / Password123!");
        }

        // --- seed a couple of promotions (only if none exist) ---
        if (promotionService.count() == 0) {
            LocalDate today = LocalDate.now();
            promotionService.saveAll(List.of(
                Promotion.builder()
                    .title("20% OFF Giant Cookie")
                    .blurb("Use code COOKIE20 at checkout.")
                    .badge("LIMITED")
                    .promoCode("COOKIE20")
                    .startsAt(today.minusDays(1))
                    .endsAt(today.plusDays(7))
                    .imageUrl("/images/hero-cookie.jpg")
                    .active(true)
                    .build(),
                Promotion.builder()
                    .title("Free Hand-Piped Message")
                    .blurb("Personalize your cookie—on us!")
                    .badge("NEW")
                    .startsAt(today.minusDays(2))
                    .endsAt(today.plusDays(3))
                    .active(true)
                    .build()
            ));
            System.out.println("Seeded demo promotions.");
        }
    }
}
