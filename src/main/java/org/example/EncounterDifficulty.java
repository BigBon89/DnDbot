package org.example;

public enum EncounterDifficulty {
    EASY, NORMAL, HARD;

    public static String[] getStringValues() {
        return new String[] { EASY.name(), NORMAL.name(), HARD.name() };
    }
}
