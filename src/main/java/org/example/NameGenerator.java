package org.example;

import java.util.Random;

public abstract class NameGenerator {
    protected Random random;
    protected String[] prefixes;
    protected String[] roots;
    protected String[] suffixes;

    public NameGenerator() {
        random = new Random();
    }

    public NameGenerator(int seed) {
        random = new Random(seed);
    }

    public String generateName() {
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String root = roots[random.nextInt(roots.length)];
        String suffix = suffixes[random.nextInt(suffixes.length)];
        return prefix + root + suffix;
    }

    protected abstract void initializeNames();
}
