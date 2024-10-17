package org.example;

public class ClassNameGenerator extends NameGenerator {
    public ClassNameGenerator() {
        super();
        initializeNames();
    }

    public ClassNameGenerator(int seed) {
        super(seed);
        initializeNames();
    }

    public void initializeNames() {
        prefixes = new String[]{"Barbarian", "Bard", "Cleric", "Druid"};
        roots = new String[]{""};
        suffixes = new String[]{""};
    }
}
