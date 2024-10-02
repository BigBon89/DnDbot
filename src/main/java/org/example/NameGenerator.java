package org.example;

import java.util.Random;

public abstract class NameGenerator {
    protected Random random;

    public NameGenerator() {
        random = new Random();
    }

    public abstract String generateName();

    public abstract String generateName(Random seededRandom);
}
