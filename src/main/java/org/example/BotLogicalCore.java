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
            System.out.println("Commands:\nhelp\ngenerate city\nroll 'formula'\nd20 'modifier' 'normal/advantage/disadvantage'\ngenerate name");
        else if (command.equals("generate city"))
            System.out.println("Generated city name: " + cityNameGenerator.generateName());
        else if (command.split(" ")[0].equals("roll"))
            System.out.println("Rolled " + dice.roll(new DiceCombination(command.split(" ")[1])));
        else if (command.split(" ")[0].equals("d20"))
            System.out.println("Rolled " + dice.d20Test(Integer.valueOf(command.split(" ")[1]), Dice.D20State.valueOf(command.split(" ")[2])));
        else if (command.equals("generate name"))
            System.out.println("Generated character name: " + characterNameGenerator.generateName());
        else
            System.out.println("Unknown command, type help for help");
    }
}
