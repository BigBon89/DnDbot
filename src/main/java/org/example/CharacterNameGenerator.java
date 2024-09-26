package org.example;

import java.util.Random;

public class CharacterNameGenerator {
    //TODO: вынести Random в поле
    public String generateCharacterName() {
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    private final String[] firstNames =
            {
                    "Aragorn", "Elwyn", "Frodo", "Gandalf", "Legolas", "Boromir", "Thranduil", "Galadriel", "Eowyn", "Gimli"
            };

    private final String[] lastNames =
            {
                    "Stormwind", "Oakenshield", "Elfsong", "Silverblade", "Brightstar", "Ironfoot", "Dragonslayer", "Shadowalker"
            };
}
