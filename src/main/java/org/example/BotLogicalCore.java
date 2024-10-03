package org.example;

public class BotLogicalCore {
    private final CityNameGenerator cityNameGenerator;
    private final CharacterNameGenerator characterNameGenerator;
    private final Dice dice;
    private final InputOutput ioHandler;

    public BotLogicalCore() {
        cityNameGenerator = new CityNameGenerator();
        characterNameGenerator = new CharacterNameGenerator();
        dice = new Dice();
        ioHandler = new Console();
    }

    public void start() {
        while (true) {
            Command command = new Command(ioHandler.getText());
            commandHandler(command);
        }
    }

    public void commandHandler(Command command) {
        if (command.getCommand().equals("help"))
            ioHandler.print("Commands:\nhelp\ngenerate_city\nroll 'formula'\nd20 'modifier' 'normal/advantage/disadvantage'\ngenerate_name");
        else if (command.getCommand().equals("generate_city"))
            ioHandler.print("Generated city name: " + cityNameGenerator.generateName());
        else if (command.getCommand().equals("roll"))
            ioHandler.print("Rolled " + dice.roll(new DiceCombination(command.getArguments()[0])));
        else if (command.getCommand().equals("d20"))
            ioHandler.print("Rolled " + dice.d20Test(Integer.valueOf(command.getArguments()[0]), Dice.D20State.valueOf(command.getArguments()[1])));
        else if (command.getCommand().equals("generate_name"))
            ioHandler.print("Generated character name: " + characterNameGenerator.generateName());
        else
            ioHandler.print("Unknown command, type help for help");
    }
}
