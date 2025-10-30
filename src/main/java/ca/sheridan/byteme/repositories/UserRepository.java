package ca.sheridan.byteme.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.sheridan.byteme.beans.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
