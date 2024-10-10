package org.example;

import java.util.Random;

public class CharacterNameGenerator extends NameGenerator {
    public CharacterNameGenerator() {
        super();
        prefixes = new String[]{"Aragorn", "Elwyn", "Frodo", "Gandalf", "Legolas", "Boromir", "Thranduil", "Galadriel", "Eowyn", "Gimli"};
        roots = new String[]{" Stormwind", " Oakenshield", " Elfsong", " Silverblade", " Brightstar", " Ironfoot", " Dragonslayer", " Shadowalker"};
        suffixes = new String[]{""};
    }

    public CharacterNameGenerator(int seed) {
        super(seed);
        prefixes = new String[]{"Aragorn", "Elwyn", "Frodo", "Gandalf", "Legolas", "Boromir", "Thranduil", "Galadriel", "Eowyn", "Gimli"};
        roots = new String[]{" Stormwind", " Oakenshield", " Elfsong", " Silverblade", " Brightstar", " Ironfoot", " Dragonslayer", " Shadowalker"};
        suffixes = new String[]{""};
    }
}
