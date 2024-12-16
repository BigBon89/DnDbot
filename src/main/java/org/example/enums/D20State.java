package org.example.enums;

import java.util.Arrays;

public enum D20State {
    NORMAL, ADVANTAGE, DISADVANTAGE;

    public static String[] getStringValues() {
        return Arrays.stream(D20State.values())
            .map(Enum::name)
            .toArray(String[]::new);
    }
}
