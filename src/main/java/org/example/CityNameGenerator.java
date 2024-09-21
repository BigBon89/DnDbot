package org.example;
import java.util.Random;

public class CityNameGenerator
{
    public CityNameGenerator()
    {

    }

    public String GenerateCityName()
    {
        Random random = new Random();
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String root = roots[random.nextInt(roots.length)];
        String suffix = suffixes[random.nextInt(suffixes.length)];
        return prefix + root + suffix;
    }

    private static String[] prefixes =
    {
        "Dark", "Bright", "Storm", "Wind", "Shadow", "Sun", "Silver", "Iron", "Gold", "Wolf", "Dragon"
    };

    private static String[] roots =
    {
        "vale", "wood", "fall", "stone", "ford", "ridge", "keep", "haven", "burg", "crest", "cliff"
    };

    private static String[] suffixes =
    {
        "ton", "burgh", "port", "holm", "stead", "ford", "bridge", "field", "gate", "shire", "hill"
    };

}
