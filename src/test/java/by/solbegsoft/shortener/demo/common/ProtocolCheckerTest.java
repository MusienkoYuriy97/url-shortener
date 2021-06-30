package by.solbegsoft.shortener.demo.common;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


class ProtocolCheckerTest {

    @ParameterizedTest
    @CsvSource({"http://google.com","google.com","www.google.com"})
    void validate(String test) {
        String validate = ProtocolChecker.setPrefix(test);

        assertTrue(validate.startsWith("http://") ||
                            validate.startsWith("https://"));
    }
}