package org.example;

import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharacterNameGenerator {
    private CharacterNameGenerator characterNameGenerator;

    TestCharacterNameGenerator() {
        characterNameGenerator = new CharacterNameGenerator();
    }

    public void checkCharacterNameGenerator() {
        String res = characterNameGenerator.generateName(new Random(1));
        assertEquals("Boromir Stormwind", res);
    }
}
