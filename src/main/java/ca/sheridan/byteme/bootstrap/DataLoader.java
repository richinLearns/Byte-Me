package ca.sheridan.byteme.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridan.byteme.models.Role;
import ca.sheridan.byteme.models.User;
import ca.sheridan.byteme.repositories.RoleRepository;
import ca.sheridan.byteme.repositories.UserRepository;

/**
 * CommandLineRunner runs the run() method immediately after the Spring application context has loaded.
 * This is used for initial data population.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Dependency Injection via constructor
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            loadInitialData();
        }
    }

    private void loadInitialData() {
        System.out.println("Loading initial user and role data...");

        // 1. Create and save all roles
        Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
        Role employeeRole = roleRepository.save(new Role("ROLE_EMPLOYEE"));
        Role customerRole = roleRepository.save(new Role("ROLE_CUSTOMER"));

        // 2. Create the Admin user
        User admin = User.builder()
                .email("admin@byteme.ca")
                .password("{noop}adminpass") // {noop} means no password encoding for now
                .build();
        admin.addRole(adminRole);
        userRepository.save(admin);

        // 3. Create the Employee user
        User employee = User.builder()
                .email("employee@byteme.ca")
                .password("{noop}employeepass")
                .build();
        employee.addRole(employeeRole);
        userRepository.save(employee);

        // 4. Create the Customer user
        User customer = User.builder()
                .email("customer@byteme.ca")
                .password("{noop}customerpass")
                .build();
        customer.addRole(customerRole);
        userRepository.save(customer);

        System.out.println("Database initialization complete.");
        System.out.println("Total Users: " + userRepository.count());
        System.out.println("Total Roles: " + roleRepository.count());
        System.out.println("--- Test Credentials ---");
        System.out.println("Admin: admin@byteme.ca / adminpass");
        System.out.println("Employee: employee@byteme.ca / employeepass");
        System.out.println("Customer: customer@byteme.ca / customerpass");
    }
}
