package org.example;

import java.util.Random;

public class Dice {

    private final Random random;

    public Dice() {
        random = new Random();
    }

    public Dice(int seed) {
        random = new Random(seed);
    }

    public String roll(DiceCombination combination) {
        if (!combination.isGood()) {
            return "Wrong spelling";
        }
        return "Rolled " + combination.getResult(random);
    }


    public Integer d20Test(int modifier, D20State d20State) {
        int firstRoll = random.nextInt(20) + 1;
        int secondRoll = random.nextInt(20) + 1;
        if (d20State == D20State.ADVANTAGE) {
            return Math.max(firstRoll, secondRoll);
        } else if (d20State == D20State.DISADVANTAGE) {
            return Math.min(firstRoll, secondRoll);
        }
        return firstRoll + modifier;
    }
}
