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
        String expect = "Rolled 4";
        assertEquals(expect, res);
    }

    @Test
    public void checkRollNegative() {
        String res = dice.roll(new DiceCombination("2d4+"));
        String expect = "Wrong spelling";
        assertEquals(expect, res);
    }
}