package by.solbegsoft.shortener.demo.controller;

import by.solbegsoft.shortener.demo.model.dto.UserCreateDto;
import by.solbegsoft.shortener.demo.service.UrlService;
import by.solbegsoft.shortener.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UrlService urlService;

    @Autowired
    public UserController(UserService userService, UrlService urlService) {
        this.userService = userService;
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<?> registration(@Valid @RequestBody UserCreateDto userCreateDto){
        userService.save(userCreateDto);
        return new ResponseEntity<>("successful added", HttpStatus.ACCEPTED);
    }

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('link:read')")
    public ResponseEntity<?> getAllLink(){
        return new ResponseEntity<>("hello",HttpStatus.OK);
    }
}