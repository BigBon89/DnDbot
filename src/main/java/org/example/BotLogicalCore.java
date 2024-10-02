package org.example;

public class BotLogicalCore {
    private final CityNameGenerator cityNameGenerator;
    private final CharacterNameGenerator characterNameGenerator;
    private final Dice dice;
    private final Console ioHandler;

    public BotLogicalCore() {
        cityNameGenerator = new CityNameGenerator();
        characterNameGenerator = new CharacterNameGenerator();
        dice = new Dice();
        ioHandler = new Console();
    }

    public void start() {
        while (true) {
            commandHandler(ioHandler.getText());
        }
    }

    public void commandHandler(String command) {
        if (command.equals("help"))
            ioHandler.print("Commands:\nhelp\ngenerate city\nroll 'formula'\nd20 'modifier' 'normal/advantage/disadvantage'\ngenerate name");
        else if (command.equals("generate city"))
            ioHandler.print("Generated city name: " + cityNameGenerator.generateName());
        else if (command.split(" ")[0].equals("roll"))
            ioHandler.print("Rolled " + dice.roll(new DiceCombination(command.split(" ")[1])));
        else if (command.split(" ")[0].equals("d20"))
            ioHandler.print("Rolled " + dice.d20Test(Integer.valueOf(command.split(" ")[1]), Dice.D20State.valueOf(command.split(" ")[2])));
        else if (command.equals("generate name"))
            ioHandler.print("Generated character name: " + characterNameGenerator.generateName());
        else
            ioHandler.print("Unknown command, type help for help");
    }
}
