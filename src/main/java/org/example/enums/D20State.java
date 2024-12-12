package org.example.enums;

public enum D20State {
    NORMAL, ADVANTAGE, DISADVANTAGE;

    public static String[] getStringValues() {
        return new String[] { NORMAL.name(), ADVANTAGE.name(), DISADVANTAGE.name() };
    }
}
