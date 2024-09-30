package org.example;

import java.util.Random;

public class Dice {
    Random random = new Random();
    public Integer roll(DiceCombination combination) {
        return combination.Result();
    }

    public Integer d20Test(int state, int modifier) //state: 0 - normal, 1 - advantage, -1 - disadvantage
    {
        int result = 0;
        if (state == 1) {
            result = random.nextInt(20) + 1;
            result = Math.max(result, random.nextInt(20) + 1);
        } else if (state == -1) {
            result = random.nextInt(20) + 1;
            result = Math.min(result, random.nextInt(20) + 1);
        } else {
            result = random.nextInt(20) + 1;
        }
        return result + modifier;
    }
}
