package org.example;

import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassNameGenerator {
    private ClassNameGenerator classNameGenerator;

    public TestClassNameGenerator() {
        classNameGenerator = new ClassNameGenerator(1);
    }

    @Test
    public void checkClassNameGenerator() {
        String res = classNameGenerator.generateName();
        assertEquals("Cleric", res);
    }
}