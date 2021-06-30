package by.solbegsoft.shortener.demo.service;

import by.solbegsoft.shortener.demo.model.Url;

import by.solbegsoft.shortener.demo.repository.UrlRepository;

import by.solbegsoft.shortener.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.mockito.Mock;


import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlServiceTest {
    @Mock
    private UrlRepository urlRepository;
    @Mock
    private UserRepository userRepository;
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        urlService = new UrlService(urlRepository, userRepository);

    }

    @Test
    void getOriginUrlByShortUrl() {
        Url url = new Url(
                "google.com",
                "12345");
        BDDMockito
                .given(urlRepository.getByShortUrlKey("12345"))
                .willReturn(Optional.of(url));

        String originUrlByShortUrl = urlService.getOriginUrlByShortUrl("12345");

        assertEquals(url.getOriginUrl(), originUrlByShortUrl);
    }

    @Test
    void create() {
    }
}