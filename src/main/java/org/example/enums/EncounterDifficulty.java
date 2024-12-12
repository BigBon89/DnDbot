package org.example.enums;

public enum EncounterDifficulty {
    EASY, NORMAL, HARD;

    public static String[] getStringValues() {
        return new String[] { EASY.name(), NORMAL.name(), HARD.name() };
    }
}
