package by.solbegsoft.shortener.demo.repository;

import by.solbegsoft.shortener.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> getByEmail(String email);
}