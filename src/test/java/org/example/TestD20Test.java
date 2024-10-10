package org.example;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestD20Test {
    private Dice dice;

    public TestD20Test() {
        dice = new Dice(1);
    }

    public void checkD20() {
        int res = dice.d20Test(1, Dice.D20State.NORMAL);
        assertEquals(7, res);
    }
}
