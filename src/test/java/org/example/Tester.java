package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Tester {
@Test
    public void checkCharacterNameGenerator() {
        CharacterNameGenerator characterNameGenerator = new CharacterNameGenerator();
        String res = characterNameGenerator.generateCharacterName();
        assertNotNull(res);
    }
@Test
    public void checkCityNameGenerator() {
        CityNameGenerator cityNameGenerator = new CityNameGenerator();
        String res = cityNameGenerator.generateCityName();
        assertNotNull(res);
    }
@Test
    public void checkRoll() {
        Dice dice = new Dice();
        int res = dice.roll("2d4");
        assertNotNull(res);
    }
}
