package org.example;

import java.util.Random;

public class CharacterNameGenerator {
    private Random random;

    private final String[] firstNames =
            {
                    "Aragorn", "Elwyn", "Frodo", "Gandalf", "Legolas", "Boromir", "Thranduil", "Galadriel", "Eowyn", "Gimli"
            };

    private final String[] lastNames =
            {
                    "Stormwind", "Oakenshield", "Elfsong", "Silverblade", "Brightstar", "Ironfoot", "Dragonslayer", "Shadowalker"
            };

    public CharacterNameGenerator()
    {
        random = new Random();
    }

    public String generateCharacterName() {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }
}
