package org.example;

public class BotLogicalCore {
    private CityNameGenerator cityNameGenerator;
    private CharacterNameGenerator characterNameGenerator;
    private Dice dice;

    public BotLogicalCore() {
        cityNameGenerator = new CityNameGenerator();
        characterNameGenerator = new CharacterNameGenerator();
        dice = new Dice();
    }

    public void commandHandler(String command) {
        if (command.equals("help"))
            System.out.println("Commands:\nhelp\ngenerate city\nroll 'formula'\ngenerate name");
        else if (command.equals("generate city"))
            System.out.println("Generated city name: " + cityNameGenerator.generateCityName());
        else if (command.split(" ")[0].equals("roll"))
            System.out.println("Rolled " + dice.roll(command.split(" ")[1]));
        else if (command.equals("generate name"))
            System.out.println("Generated character name: " + characterNameGenerator.generateCharacterName());
        else
            System.out.println("Unknown command, type help for help");
    }
}
