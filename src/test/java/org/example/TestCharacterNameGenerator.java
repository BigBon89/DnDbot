package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCharacterNameGenerator {
    public void check() {
        CharacterNameGenerator characterNameGenerator = new CharacterNameGenerator();
        String res = characterNameGenerator.generateCharacterName();
        assertNotNull(res);
    }
}
