package ca.sheridan.byteme;

import ca.sheridan.byteme.beans.User;
import ca.sheridan.byteme.beans.Role;
import ca.sheridan.byteme.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String adminEmail = "admin@cookie.com";
    private final String rawPassword = "Password123!";

    @BeforeEach
    void setUpDemoUser() {
        userRepository.deleteAll(); // Optional: clean slate for test isolation

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = User.builder()
                    .name("Admin User")
                    .email(adminEmail)
                    .password(passwordEncoder.encode(rawPassword))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }

    @Test
    void loginPageShowsFormFields() throws Exception {
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("name=\"username\"")))
            .andExpect(content().string(containsString("name=\"password\"")))
            .andExpect(content().string(containsString("Login - Cookiegram Bakery")));
    }

    @Test
    void loginFlowSucceedsAndDashboardAccessible() throws Exception {
        MvcResult result = mockMvc.perform(formLogin().user(adminEmail).password(rawPassword))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/dashboard"))
            .andReturn();

        MockHttpSession session = (MockHttpSession) result.getRequest().getSession(false);
        mockMvc.perform(get("/dashboard").session(session))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("System Administration")))
            .andExpect(content().string(containsString("Admin Panel")));
    }
}