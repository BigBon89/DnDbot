package org.example;

import java.util.Random;

public class CityNameGenerator extends NameGenerator {
    public CityNameGenerator() {
        super();
        initializeNames();
    }

    public CityNameGenerator(int seed) {
        super(seed);
        initializeNames();
    }

    public void initializeNames() {
        prefixes = new String[]{"Dark", "Bright", "Storm",
                "Wind", "Shadow", "Sun",
                "Silver", "Iron", "Gold",
                "Wolf", "Dragon"
        };
        roots = new String[]{"vale", "wood", "fall",
                "stone", "ford", "ridge",
                "keep", "haven", "burg",
                "crest", "cliff"
        };
        suffixes = new String[]{"ton", "burgh", "port",
                "holm", "stead", "ford",
                "bridge", "field", "gate",
                "shire", "hill"
        };
    }
}
