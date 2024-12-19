package org.example;

import org.example.enums.D20State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestD20Test {
    private final Dice dice;

    public TestD20Test() {
        dice = new Dice(1);
    }

    @Test
    public void checkD20() {
        int res = dice.d20Test(1, D20State.NORMAL);
        int expect = 7;
        assertEquals(expect, res);
    }
}