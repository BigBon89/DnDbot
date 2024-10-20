package org.example;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestD20Test {
    private Dice dice;

    public TestD20Test() {
        dice = new Dice(1);
    }

    @Test
    public void checkD20() {
        int res = dice.d20Test(1, D20State.NORMAL);
        assertEquals(6, res);
    }
}
