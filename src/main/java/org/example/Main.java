package org.example;

import org.example.namegenerators.CharacterNameGenerator;
import org.example.namegenerators.CityNameGenerator;
import org.example.namegenerators.ClassNameGenerator;

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