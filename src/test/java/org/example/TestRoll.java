package org.example;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestRoll {
    private Dice dice;

    public TestRoll() {
        dice = new Dice();
    }

    public void check() {
        int res = dice.roll(new DiceCombination("2d4"), new Random(1));
        assertNotNull(res);
    }
}
