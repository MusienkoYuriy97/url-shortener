package by.solbegsoft.shortener.demo.service;

import by.solbegsoft.shortener.demo.model.User;
import by.solbegsoft.shortener.demo.model.dto.UserCreateDto;
import by.solbegsoft.shortener.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.solbegsoft.shortener.demo.model.UserRole.*;
import static by.solbegsoft.shortener.demo.model.UserStatus.*;


@Slf4j
@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        log.info("User Service initialized" + passwordEncoder + " " + userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserCreateDto userCreateDto){
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            log.warn("User with this email already exist");
            throw new RuntimeException();
        }
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setEmail(userCreateDto.getEmail());
        user.setUserRole(USER);
        user.setUserStatus(ACTIVE);
        log.debug("Try save user " + user);
        User save = userRepository.save(user);
        log.debug("Saved user " + save);
    }
}