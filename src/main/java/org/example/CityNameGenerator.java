package org.example;

import java.util.Random;

public class CityNameGenerator {
    private Random random;

    private final String[] prefixes =
            {
                    "Dark", "Bright", "Storm", "Wind", "Shadow", "Sun", "Silver", "Iron", "Gold", "Wolf", "Dragon"
            };

    private final String[] roots =
            {
                    "vale", "wood", "fall", "stone", "ford", "ridge", "keep", "haven", "burg", "crest", "cliff"
            };

    private final String[] suffixes =
            {
                    "ton", "burgh", "port", "holm", "stead", "ford", "bridge", "field", "gate", "shire", "hill"
            };

    public CityNameGenerator()
    {
        random = new Random();
    }

    public String generateCityName() {
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String root = roots[random.nextInt(roots.length)];
        String suffix = suffixes[random.nextInt(suffixes.length)];
        return prefix + root + suffix;
    }
}
