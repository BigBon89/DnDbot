package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCharacterNameGenerator {
    private CharacterNameGenerator characterNameGenerator;

    TestCharacterNameGenerator() {
        characterNameGenerator = new CharacterNameGenerator();
    }

    public void check() {
        String res = characterNameGenerator.generateCharacterName();
        assertNotNull(res);
    }
}
