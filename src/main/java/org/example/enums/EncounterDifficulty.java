package org.example.enums;

import java.util.Arrays;

public enum EncounterDifficulty {
    EASY, NORMAL, HARD;

    public static String[] getStringValues() {
        return Arrays.stream(EncounterDifficulty.values())
            .map(Enum::name)
            .toArray(String[]::new);
    }
}
