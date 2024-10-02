package org.example;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoll {
    private Dice dice;

    public TestRoll() {
        dice = new Dice();
    }

    public void checkRoll() {
        int res = dice.roll(new DiceCombination("2d4"), new Random(1));
        assertEquals(4, res);
    }
}
