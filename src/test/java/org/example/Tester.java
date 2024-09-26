package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Tester {
    private TestCharacterNameGenerator testCharacterNameGenerator;
    private TestCityNameGenerator testCityNameGenerator;
    private TestRoll testRoll;

    @Test
    public void checkCharacterNameGenerator() {
        testCharacterNameGenerator = new TestCharacterNameGenerator();
        testCharacterNameGenerator.check();
    }

    @Test
    public void checkCityNameGenerator() {
        testCityNameGenerator = new TestCityNameGenerator();
        testCityNameGenerator.check();
    }

    @Test
    public void checkRoll() {
        testRoll = new TestRoll();
        testRoll.check();
    }
}
