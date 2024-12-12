package org.example;

import org.example.name_generators.CharacterNameGenerator;
import org.example.name_generators.CityNameGenerator;
import org.example.name_generators.ClassNameGenerator;

public class Main {
    public static void main(String[] args) {
        BotLogicalCore botLogicalCore = new BotLogicalCore(
                new CityNameGenerator(),
                new CharacterNameGenerator(),
                new ClassNameGenerator(),
                new Dice(),
                new Console(),
                new Encounter()
        );

        //botLogicalCore.startConsole();
        botLogicalCore.startGui();
    }
}