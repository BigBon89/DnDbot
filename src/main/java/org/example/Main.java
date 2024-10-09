package org.example;

public class Main {
    public static void main(String[] args) {
        BotLogicalCore botLogicalCore = new BotLogicalCore(new CityNameGenerator(), new CharacterNameGenerator(), new Dice(), new Console());

        botLogicalCore.start();
    }
}