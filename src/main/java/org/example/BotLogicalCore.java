package org.example;

public class BotLogicalCore {
    private final CityNameGenerator cityNameGenerator;
    private final CharacterNameGenerator characterNameGenerator;
    private final ClassNameGenerator classNameGenerator;
    private final Dice dice;
    private final InputOutput ioHandler;
    private Encounter encounter;

    public BotLogicalCore(CityNameGenerator cityNameGenerator,
        CharacterNameGenerator characterNameGenerator,
        ClassNameGenerator classNameGenerator,
        Dice dice,
        InputOutput inputOutput,
        Encounter encounter
    ) {
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
        if (!command.isValid()) {
            ioHandler.print("Unknown command, type 'help' for available commands.");
            return;
        }
        try {
            switch (command.getCommandType()) {
                case HELP:
                    ioHandler.print(Commands.helpCommand());
                    break;
                case GENERATE_CITY:
                    ioHandler.print("Generated city name: " + cityNameGenerator.generateName());
                    break;
                case GENERATE_CLASS:
                    ioHandler.print("Generated class name: " + classNameGenerator.generateName());
                    break;
                case ROLL:
                    ioHandler.print(dice.roll(new DiceCombination(command.getArguments()[0])));
                    break;
                case D20:
                    ioHandler.print("Rolled " + dice.d20Test(Integer.parseInt(command.getArguments()[0]),
                            D20State.valueOf(command.getArguments()[1]))
                    );
                    break;
                case GENERATE_NAME:
                    ioHandler.print("Generated character name: " + characterNameGenerator.generateName());
                    break;
                case GENERATE_ENCOUNTER:
                    ioHandler.print(encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]),
                            Integer.parseInt(command.getArguments()[1]), Integer.parseInt(command.getArguments()[2]),
                            "")
                    );
                    break;
                case GENERATE_ENCOUNTER_FILTER:
                    ioHandler.print(encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]),
                            Integer.parseInt(command.getArguments()[1]),
                            Integer.parseInt(command.getArguments()[2]),
                            command.getArguments()[3])
                    );
                    break;
                case ENCOUNTER_END:
                    ioHandler.print(encounter.end());
                    break;
                case ATTACK:
                    encounter.attack(Integer.parseInt(command.getArguments()[0]),
                            Integer.parseInt(command.getArguments()[1])
                    );
                    ioHandler.print(encounter.printMonsters());
                    break;
                default:
                    ioHandler.print("Unknown command, type help for help");
                    break;
            }
        } catch (IllegalArgumentException e) {
            ioHandler.print("Unknown command, type help for help");
        }
    }
}