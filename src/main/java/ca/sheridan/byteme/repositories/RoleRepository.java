package ca.sheridan.byteme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridan.byteme.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    // Custom method to easily find a Role by its name
    Optional<Role> findByRoleName(String roleName);
}
