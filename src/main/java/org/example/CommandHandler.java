package org.example;

public class CommandHandler {
    private final CityNameGenerator cityNameGenerator;
    private final CharacterNameGenerator characterNameGenerator;
    private final ClassNameGenerator classNameGenerator;
    private final Dice dice;
    private final Encounter encounter;

    public CommandHandler(CityNameGenerator cityNameGenerator,
        CharacterNameGenerator characterNameGenerator,
        ClassNameGenerator classNameGenerator,
        Dice dice,
        Encounter encounter
    ) {
        this.cityNameGenerator = cityNameGenerator;
        this.characterNameGenerator = characterNameGenerator;
        this.classNameGenerator = classNameGenerator;
        this.dice = dice;
        this.encounter = encounter;
    }

    public String handleCommand(Command command) {
        if (!command.isValid()) {
            return "Unknown command, type 'help' for available commands.";
        }
        try {
            switch (command.getCommandType()) {
                case HELP:
                    return Commands.helpCommand();
                case GENERATE_CITY:
                    return "Generated city name: " + cityNameGenerator.generateName();
                case GENERATE_CLASS:
                    return "Generated class name: " + classNameGenerator.generateName();
                case ROLL:
                    return dice.roll(new DiceCombination(command.getArguments()[0]));
                case D20:
                    return "Rolled " + dice.d20Test(Integer.parseInt(command.getArguments()[0]),
                            D20State.valueOf(command.getArguments()[1])
                    );
                case GENERATE_NAME:
                    return "Generated name: " + characterNameGenerator.generateName();
                case GENERATE_ENCOUNTER:
                    return encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]),
                            Integer.parseInt(command.getArguments()[1]), Integer.parseInt(command.getArguments()[2]),
                            ""
                    );
                case GENERATE_ENCOUNTER_FILTER:
                    return encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]),
                            Integer.parseInt(command.getArguments()[1]),
                            Integer.parseInt(command.getArguments()[2]),
                            command.getArguments()[3]
                    );
                case ENCOUNTER_END:
                    return encounter.end();
                case ATTACK:
                    return encounter.attack(Integer.parseInt(command.getArguments()[0]),
                            Integer.parseInt(command.getArguments()[1]
                            ));
                default:
                    return "Unknown command, type help for help";
            }
        } catch (IllegalArgumentException e) {
            return "Unknown command, type help for help";
        }
    }
}
