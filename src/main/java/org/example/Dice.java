package org.example;

import java.util.Random;

public class Dice {

    protected Random random;
    public Dice() {random = new Random();}
    public Dice(int seed) {random = new Random(seed);}

    public String roll(DiceCombination combination) {
        if(!combination.isGood())
        {
            return "Wrong spelling";
        }
        return "Rolled " + Integer.toString(combination.getResult(random));
    }


    public Integer d20Test(int modifier, D20State.d20StateEnum d20State)
    {
        int firstRoll = random.nextInt(20) + 1;
        int secondRoll = random.nextInt(20) + 1;
        if(d20State == D20State.d20StateEnum.ADVANTAGE){
            return Math.max(firstRoll, secondRoll);
        } else if (d20State == D20State.d20StateEnum.DISADVANTAGE) {
            return Math.min(firstRoll, secondRoll);
        }
        return firstRoll;
    }
}
