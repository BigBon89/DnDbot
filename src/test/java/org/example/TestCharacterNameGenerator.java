package org.example;

import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharacterNameGenerator {
    private final CharacterNameGenerator characterNameGenerator;

    TestCharacterNameGenerator() {
        characterNameGenerator = new CharacterNameGenerator(1);
    }

    @Test
    public void checkCharacterNameGenerator() {
        String res = characterNameGenerator.generateName();
        assertEquals("Boromir Stormwind", res);
    }
}
