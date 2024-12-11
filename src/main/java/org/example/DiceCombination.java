package org.example;

import java.util.Random;

public class DiceCombination {
    private String combination;

    public DiceCombination(String combination) {
        this.combination = combination;
    }

    public Integer getResult(Random random) {
        String[] dice = combination.split("\\+");
        int result = 0;
        for (String die : dice) {
            if (die.contains("d")) {
                int diceCount = Integer.parseInt(die.split("d")[0]);
                int diceType = Integer.parseInt(die.split("d")[1]);
                for (int j = 0; j < diceCount; j++) {
                    result += random.nextInt(diceType) + 1;
                }
            } else {
                result += Integer.parseInt(die);
            }
        }
        return result;
    }

    public boolean isGood() {
        String[] dice = combination.split("\\+");
        for (String die : dice) {
            if (!die.matches(".*\\d.*|d")) {
                return false;
            }
            if (die.isEmpty()) {
                return false;
            }
            if (die.startsWith("d") || die.endsWith("d")) {
                return false;
            }
        }
        if (combination.contains("++") || combination.startsWith("+") || combination.endsWith("+")) {
            return false;
        }
        return true;
    }

}