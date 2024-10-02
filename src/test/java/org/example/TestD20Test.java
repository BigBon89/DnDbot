package org.example;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestD20Test {
    private Dice dice;

    public TestD20Test() {
        dice = new Dice();
    }

    public void check() {
        int res = dice.d20Test(1, Dice.D20State.NORMAL, new Random(1));
        assertEquals(7, res);
    }
}
