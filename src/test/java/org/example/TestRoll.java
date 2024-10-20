package org.example;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoll {
    private Dice dice;

    public TestRoll() {
        dice = new Dice(1);
    }

    @Test
    public void checkRoll() {
        String res = dice.roll(new DiceCombination("2d4"));
        assertEquals("Rolled 4", res);
    }
}
