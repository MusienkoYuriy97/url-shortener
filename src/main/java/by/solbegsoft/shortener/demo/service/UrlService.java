package by.solbegsoft.shortener.demo.service;

import by.solbegsoft.shortener.demo.common.StringGenerator;
import by.solbegsoft.shortener.demo.exception.ShortUrlNotFoundException;
import by.solbegsoft.shortener.demo.model.Url;
import by.solbegsoft.shortener.demo.model.User;
import by.solbegsoft.shortener.demo.model.dto.UrlCreateDto;
import by.solbegsoft.shortener.demo.repository.UrlRepository;
import by.solbegsoft.shortener.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UrlService {
    private UrlRepository urlRepository;
    private UserRepository userRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository, UserRepository userRepository) {
        log.debug("UrlRepository " + urlRepository);
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    public String getOriginUrlByShortUrl(String shortUrl){
        log.debug("Method getOriginUrlByShortUrl is started with param shortUrl=" + shortUrl);
        Optional<Url> byShortUrl = urlRepository.getByShortUrlKey(shortUrl);

        if (byShortUrl.isPresent()) {
            String originUrl = byShortUrl
                    .get()
                    .getOriginUrl();
            return originUrl;
        }else {
            log.warn("Short url" + shortUrl + " doesn't exist");
            throw new ShortUrlNotFoundException();
        }
    }

    public Url save(UrlCreateDto urlCreateDto) {
        User user;
        if (userRepository.existsByEmail(urlCreateDto.getEmail())) {
            user = userRepository
                    .getByEmail(urlCreateDto.getEmail())
                    .get();
        }else {
            throw new RuntimeException();
        }

        Url url = new Url();
        if (urlRepository.existsByOriginUrlAndAndUser(urlCreateDto.getOriginUrl(), user)){
            url = urlRepository.getByOriginUrlAndUser(urlCreateDto.getOriginUrl(), user).get();
            log.debug("Get exist url from database: " + url);
        }else {
            url.setOriginUrl(urlCreateDto.getOriginUrl());
            url.setShortUrlKey(StringGenerator.generate(10));
            url.setUser(user);

            url = urlRepository.save(url);
            log.debug("Create url for save in database: " + url);
        }

        log.info("Successfully save url" + url);
        return url;
    }

    public List<Url> getAllByUser(User user){
        return urlRepository
                .getAllByUser(user)
                .orElse(Collections.emptyList());
    }
}