package org.example;
import java.util.Random;

public class Dice
{
    public Integer RollDice(int diceCount, int dieType)
    {
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < diceCount; i++)
        {
            sum += random.nextInt(dieType)+1;
        }
        return sum;
    }
    public Integer Roll(String formula)
    {
        String[] dice = formula.split("&"); // должен быть + вместо &, но он не работает
        int result = 0;
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i].contains("d"))
            {
                result += RollDice(Integer.parseInt(dice[i].split("d")[0]), Integer.parseInt(dice[i].split("d")[1]));
            }
            result += Integer.parseInt(dice[i]);
        }
        return result;
    }
    public Integer D20Test(int state, int modifier) //state: 0 - normal, 1 - advantage, -1 - disadvantage
    {
        Random random = new Random();
        int result = 0;
        if(state == 1)
        {
            result = random.nextInt(20)+1;
            result = Math.max(result, random.nextInt(20)+1);
        }
        else if(state == -1)
        {
            result = random.nextInt(20)+1;
            result = Math.min(result, random.nextInt(20)+1);
        }
        else
        {
            result = random.nextInt(20)+1;
        }
        return result + modifier;
    }
}
