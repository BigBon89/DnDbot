package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FunctionalityTester {
    private TestCharacterNameGenerator testCharacterNameGenerator;
    private TestCityNameGenerator testCityNameGenerator;
    private TestClassNameGenerator testClassNameGenerator;
    private TestRoll testRoll;
    private TestD20Test testD20Test;

    public FunctionalityTester() {
        testCharacterNameGenerator = new TestCharacterNameGenerator();
        testCityNameGenerator = new TestCityNameGenerator();
        testClassNameGenerator = new TestClassNameGenerator();
        testRoll = new TestRoll();
        testD20Test = new TestD20Test();
    }

    @Test
    public void runTests() {
        testCharacterNameGenerator.checkCharacterNameGenerator();
        testCityNameGenerator.checkCityNameGenerator();
        testClassNameGenerator.checkClassNameGenerator();
        testRoll.checkRoll();
        testD20Test.checkD20();
    }
}
