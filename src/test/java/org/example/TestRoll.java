package org.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestRoll {
    private Dice dice;

    public TestRoll() {
        dice = new Dice();
    }

    public void check() {
        int res = dice.roll("2d4");
        assertNotNull(res);
    }
}
