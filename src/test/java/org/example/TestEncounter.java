package org.example;

import org.example.enums.EncounterDifficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEncounter {
    private Encounter encounter;

    public TestEncounter() {
        encounter = new Encounter(1);
    }

    @Test
    public void checkEncounterStart() {
        String res = encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String expect = "0. Kobold Veles 1/1\n"
                + "1. Stirge 1/1\n"
                + "2. Blood-Borne Ooze 2/2\n";
        assertEquals(expect, res);
    }

    @Test
    public void checkEncounterEnd() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.end();
        String expect = "The encounter is over";
        assertEquals(expect, res);
    }

    @Test
    public void checkKillMonster() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.attack(2, 4);
        String expect = "0. Kobold Veles 1/1\n"
                + "1. Stirge 1/1\n";
        assertEquals(expect, res);
    }

    @Test
    public void checkAttackMonster() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.attack(2, 1);
        String expect = "0. Kobold Veles 1/1\n"
                + "1. Stirge 1/1\n"
                + "2. Blood-Borne Ooze 1/2\n";
        assertEquals(expect, res);
    }

    @Test
    public void checkAttackInvalidDamage() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.attack(2, -5);
        String expect = "Damage must be a positive number";
        assertEquals(expect, res);
    }

    @Test
    public void checkWrongIndex() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 4, 1, "");
        String res = encounter.attack(5, 9);
        String expect = "Wrong monster index";
        assertEquals(expect, res);
    }

    @Test
    public void checkInvalidPlayersCount() {
        String res = encounter.start(EncounterDifficulty.valueOf("NORMAL"), 0, 1, "");
        String expect = "Invalid players count";
        assertEquals(expect, res);
    }

    @Test
    public void checkAttackIfEncounterNotStart() {
        String res = encounter.attack(0, 2);
        String expect = "The encounter has not started";
        assertEquals(expect, res);
    }

    @Test
    public void checkAttackKillingLastMonster() {
        encounter.start(EncounterDifficulty.valueOf("NORMAL"), 1, 1, "");
        String res = encounter.attack(0, 5);
        String expect = "All monsters is dead\n"
                + "The encounter is over";
        assertEquals(expect, res);
    }
}