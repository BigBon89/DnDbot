package org.example;

import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCityNameGenerator {
    private CityNameGenerator cityNameGenerator;

    public TestCityNameGenerator() {
        cityNameGenerator = new CityNameGenerator();
    }

    public void check() {
        String res = cityNameGenerator.generateCityName(new Random(1));
        assertEquals("Shadowkeepgate", res);
    }
}