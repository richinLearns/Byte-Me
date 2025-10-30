package ca.sheridan.byteme.repositories;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridan.byteme.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
=======
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.sheridan.byteme.beans.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
>>>>>>> Spring-Security-jwt-Set-up-with-JPA
}
