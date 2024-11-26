package org.example;

import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEncounter {
    private Encounter encounter;

    public TestEncounter() {
        encounter = new Encounter(1);
    }

    @Test
    public void checkEncounter() {
        String res = encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        assertEquals("0. Kobold Veles 1/1\n"
            + "1. Stirge 1/1\n"
            + "2. Blood-Borne Ooze 2/2", res
        );
    }

    @Test
    public void checkAttack() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.attack(2, 4);
        assertEquals("0. Kobold Veles 1/1\n"
            + "1. Stirge 1/1\n"
            + "2. Blood-Borne Ooze (DEAD)", res
        );
    }

    @Test
    public void checkWrongIndex() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.attack(5, 9);
        assertEquals("Wrong monster index", res);
    }
}