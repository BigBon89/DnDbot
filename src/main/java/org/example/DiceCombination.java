package org.example;

import java.util.Random;
public class DiceCombination {
    private String combination;

    public DiceCombination(String combination)
    {
        this.combination = combination;
    }
    public Integer result(Random random)
    {
        String[] dice = combination.split("\\+");
        int result = 0;
        for (int i = 0; i < dice.length; i++) {
            if (dice[i].contains("d")) {
                int diceCount = Integer.parseInt(dice[i].split("d")[0]);
                int dieType = Integer.parseInt(dice[i].split("d")[1]);
                for (int j = 0; j < diceCount; j++) {
                    result += random.nextInt(dieType) + 1;
                }
            } else {
                result += Integer.parseInt(dice[i]);
            }
        }
        return result;
    }
}
