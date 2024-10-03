package org.example;

import java.util.Random;

public abstract class NameGenerator {
    protected Random random;

    public NameGenerator() {
        random = new Random();
    }

    public NameGenerator(int seed) {
        random = new Random(seed);
    }

    // TODO: реализовать generateName в абстрактном методе.
    public abstract String generateName();
}
