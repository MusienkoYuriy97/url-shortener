package by.solbegsoft.shortener.demo.controller;

import by.solbegsoft.shortener.demo.common.ProtocolChecker;
import by.solbegsoft.shortener.demo.model.Url;
import by.solbegsoft.shortener.demo.model.dto.UrlCreateDto;
import by.solbegsoft.shortener.demo.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/")
public class UrlController {
    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrlKey}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrlKey){
        log.debug("shortUrlKey from user: " + shortUrlKey);
        String redirectUrl = urlService.getOriginUrlByShortUrl(shortUrlKey);
        log.debug("shortUrlKey from user: " + shortUrlKey);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(redirectUrl))
                .build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UrlCreateDto urlCreateDto){
        log.debug("Input data from user: " + urlCreateDto);
        urlCreateDto.setOriginUrl(ProtocolChecker.setPrefix(urlCreateDto.getOriginUrl()));
        Url url = urlService.save(urlCreateDto);
        log.debug("User service return: " + url);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}