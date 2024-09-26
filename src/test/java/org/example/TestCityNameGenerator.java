package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCityNameGenerator {
    private CityNameGenerator cityNameGenerator;

    public TestCityNameGenerator() {
        cityNameGenerator = new CityNameGenerator();
    }

    public void check() {
        String res = cityNameGenerator.generateCityName();
        assertNotNull(res);
    }
}