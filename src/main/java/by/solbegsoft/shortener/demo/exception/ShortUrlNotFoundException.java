package by.solbegsoft.shortener.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShortUrlNotFoundException extends RuntimeException {
    public ShortUrlNotFoundException() {
        super();
    }

    public ShortUrlNotFoundException(String message) {
        super(message);
    }

    public ShortUrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}