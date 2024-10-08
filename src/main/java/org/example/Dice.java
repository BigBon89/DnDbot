package org.example;

import java.util.Random;

public class Dice {
    Random random = new Random();
    public Integer roll(DiceCombination combination) {
        return combination.Result(random);
    }
    public Integer roll(DiceCombination combination, Random seededRandom) {
        return combination.Result(seededRandom);
    }

    public enum D20State{NORMAL, ADVANTAGE, DISADVANTAGE}

    public Integer d20Test(int modifier,D20State d20State)
    {
        int result = 0;
        if (d20State == D20State.ADVANTAGE) {
            result = random.nextInt(20) + 1;
            result = Math.max(result, random.nextInt(20) + 1);
        } else if (d20State == D20State.DISADVANTAGE) {
            result = random.nextInt(20) + 1;
            result = Math.min(result, random.nextInt(20) + 1);
        } else {
            result = random.nextInt(20) + 1;
        }
        return result + modifier;
    }
    public Integer d20Test(int modifier,D20State d20State, Random seededRandom)
    {
        int result = 0;
        if (d20State == D20State.ADVANTAGE) {
            result = seededRandom.nextInt(20) + 1;
            result = Math.max(result, seededRandom.nextInt(20) + 1);
        } else if (d20State == D20State.DISADVANTAGE) {
            result = seededRandom.nextInt(20) + 1;
            result = Math.min(result, seededRandom.nextInt(20) + 1);
        } else {
            result = seededRandom.nextInt(20) + 1;
        }
        return result + modifier;
    }
}
