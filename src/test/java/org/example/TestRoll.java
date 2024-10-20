package org.example;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoll {
    private Dice dice;

    public TestRoll() {
        dice = new Dice(1);
    }

    public void checkRoll() {
        String res = dice.roll(new DiceCombination("2d4"));
        assertEquals("Rolled 4", res);
    }
    public void checkRollNegative() {
        String res = dice.roll(new DiceCombination("2d4+"));
        assertEquals("Wrong spelling", res);
    }
}
