package org.example.name_generators;

public class CharacterNameGenerator extends NameGenerator {
    public CharacterNameGenerator() {
        super();
        initializeNames();
    }

    public CharacterNameGenerator(int seed) {
        super(seed);
        initializeNames();
    }

    public void initializeNames() {
        prefixes = new String[]{
            "Aragorn", "Elwyn", "Frodo", "Gandalf", "Legolas", "Boromir",
            "Thranduil", "Galadriel", "Eowyn", "Gimli"
        };
        roots = new String[] {
            " Stormwind", " Oakenshield", " Elfsong", " Silverblade", " Brightstar",
            " Ironfoot", " Dragonslayer", " Shadowalker"
        };
        suffixes = new String[]{""};
    }
}
