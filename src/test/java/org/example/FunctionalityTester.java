package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FunctionalityTester {
    private TestCharacterNameGenerator testCharacterNameGenerator;
    private TestCityNameGenerator testCityNameGenerator;
    private TestClassNameGenerator testClassNameGenerator;
    private TestRoll testRoll;
    private TestD20Test testD20Test;

    @Test
    public void checkCharacterNameGenerator() {
        testCharacterNameGenerator = new TestCharacterNameGenerator();
        testCharacterNameGenerator.checkCharacterNameGenerator();
    }

    @Test
    public void checkCityNameGenerator() {
        testCityNameGenerator = new TestCityNameGenerator();
        testCityNameGenerator.checkCityNameGenerator();
    }

    @Test
    public void checkClassNameGenerator() {
        testClassNameGenerator = new TestClassNameGenerator();
        testClassNameGenerator.checkClassNameGenerator();
    }

    @Test
    public void checkRoll() {
        testRoll = new TestRoll();
        testRoll.checkRoll();
    }

    @Test
    public void checkRollNegative() {
        testRoll = new TestRoll();
        testRoll.checkRollNegative();
    }

    @Test
    public void checkD20Test() {
        testD20Test = new TestD20Test();
        testD20Test.checkD20();
    }
}
