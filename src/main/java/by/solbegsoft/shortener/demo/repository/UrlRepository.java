package by.solbegsoft.shortener.demo.repository;

import by.solbegsoft.shortener.demo.model.Url;
import by.solbegsoft.shortener.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<List<Url>> getAllByUser(User user);
    Optional<Url> getByShortUrlKey(String shortUrlKey);
    Optional<Url> getByOriginUrlAndUser(String originUrl, User user);
    boolean existsByOriginUrlAndAndUser(String originUrl, User user);
}