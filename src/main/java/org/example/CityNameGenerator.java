package org.example;

import java.util.Random;

public class CityNameGenerator extends NameGenerator {
    public CityNameGenerator() {
        super();
        prefixes = new String[]{"Dark", "Bright", "Storm", "Wind", "Shadow", "Sun", "Silver", "Iron", "Gold", "Wolf", "Dragon"};
        roots = new String[]{"vale", "wood", "fall", "stone", "ford", "ridge", "keep", "haven", "burg", "crest", "cliff"};
        suffixes = new String[]{"ton", "burgh", "port", "holm", "stead", "ford", "bridge", "field", "gate", "shire", "hill"};
    }

    public CityNameGenerator(int seed) {
        super(seed);
        prefixes = new String[]{"Dark", "Bright", "Storm", "Wind", "Shadow", "Sun", "Silver", "Iron", "Gold", "Wolf", "Dragon"};
        roots = new String[]{"vale", "wood", "fall", "stone", "ford", "ridge", "keep", "haven", "burg", "crest", "cliff"};
        suffixes = new String[]{"ton", "burgh", "port", "holm", "stead", "ford", "bridge", "field", "gate", "shire", "hill"};
    }
}
