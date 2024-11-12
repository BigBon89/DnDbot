package org.example;

public class BotLogicalCore {
    private final CityNameGenerator cityNameGenerator;
    private final CharacterNameGenerator characterNameGenerator;
    private final ClassNameGenerator classNameGenerator;
    private final Dice dice;
    private final InputOutput ioHandler;
    private final Encounter encounter;

    public BotLogicalCore(CityNameGenerator cityNameGenerator, CharacterNameGenerator characterNameGenerator, ClassNameGenerator classNameGenerator, Dice dice, InputOutput inputOutput, Encounter encounter) {
        this.cityNameGenerator = cityNameGenerator;
        this.characterNameGenerator = characterNameGenerator;
        this.classNameGenerator = classNameGenerator;
        this.dice = dice;
        this.ioHandler = inputOutput;
        this.encounter = encounter;
    }

    public void start() {
        while (true) {
            Command command = new Command(ioHandler.getText());
            commandHandler(command);
        }
    }

    public void commandHandler(Command command) {
        if (command.getCommand().equals("help"))
            ioHandler.print("Commands:\nhelp\ngenerate_city\ngenerate_class\nroll 'formula'\nd20 'modifier' 'normal/advantage/disadvantage'\ngenerate_name\nencounter_start\nencounter_end");
        else if (command.getCommand().equals("generate_city"))
            ioHandler.print("Generated city name: " + cityNameGenerator.generateName());
        else if (command.getCommand().equals("generate_class"))
            ioHandler.print("Generated class name: " + classNameGenerator.generateName());
        else if (command.getCommand().equals("roll"))
            ioHandler.print(dice.roll(new DiceCombination(command.getArguments()[0])));
        else if (command.getCommand().equals("d20"))
            ioHandler.print("Rolled " + dice.d20Test(Integer.parseInt(command.getArguments()[0]), D20State.valueOf(command.getArguments()[1])));
        else if (command.getCommand().equals("generate_name"))
            ioHandler.print("Generated character name: " + characterNameGenerator.generateName());
        else if (command.getCommand().equals("encounter_start"))
            ioHandler.print(encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]), Integer.parseInt(command.getArguments()[1]),  Integer.parseInt(command.getArguments()[2]), command.getArguments()[3]));
        else if (command.getCommand().equals("encounter_end"))
            ioHandler.print(encounter.end());
        else
            ioHandler.print("Unknown command, type help for help");
    }
}