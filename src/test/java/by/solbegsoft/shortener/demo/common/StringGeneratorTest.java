package by.solbegsoft.shortener.demo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringGeneratorTest {
    private static final int LENGTH = 10;

    @Test
    void generate1() {
        String generate = StringGenerator.generate(LENGTH);
        assertTrue(generate.matches("^[a-zA-Z0-9]*$")
                && (generate.length() == LENGTH));
    }
}