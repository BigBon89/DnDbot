package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Tester {
    private TestCharacterNameGenerator testCharacterNameGenerator;
    private TestCityNameGenerator testCityNameGenerator;

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
        Dice dice = new Dice();
        int res = dice.roll("2d4");
        assertNotNull(res);
    }
}
