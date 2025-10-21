package ca.sheridan.byteme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridan.byteme.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
