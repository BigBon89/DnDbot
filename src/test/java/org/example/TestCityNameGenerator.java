package org.example;

import org.example.namegenerators.CityNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCityNameGenerator {
    private CityNameGenerator cityNameGenerator;

    public TestCityNameGenerator() {
        cityNameGenerator = new CityNameGenerator(1);
    }

    @Test
    public void checkCityNameGenerator() {
        String res = cityNameGenerator.generateName();
        String expect = "Shadowkeepgate";
        assertEquals(expect, res);
    }
}