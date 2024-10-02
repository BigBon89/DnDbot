package org.example;

import java.util.Random;

public class CharacterNameGenerator extends NameGenerator {
    private final String[] firstNames =
            {
                    "Aragorn", "Elwyn", "Frodo", "Gandalf", "Legolas", "Boromir", "Thranduil", "Galadriel", "Eowyn", "Gimli"
            };

    private final String[] lastNames =
            {
                    "Stormwind", "Oakenshield", "Elfsong", "Silverblade", "Brightstar", "Ironfoot", "Dragonslayer", "Shadowalker"
            };

    public String generateName() {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }
    public String generateName(Random random) {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }
}
