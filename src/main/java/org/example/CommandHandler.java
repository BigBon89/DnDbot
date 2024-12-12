package org.example;

import org.example.enums.Commands;
import org.example.enums.D20State;
import org.example.enums.EncounterDifficulty;
import org.example.namegenerators.CharacterNameGenerator;
import org.example.namegenerators.CityNameGenerator;
import org.example.namegenerators.ClassNameGenerator;

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
            return switch (command.getCommandType()) {
                case HELP -> Commands.helpCommand();
                case GENERATE_CITY -> "Generated city name: " + cityNameGenerator.generateName();
                case GENERATE_CLASS -> "Generated class name: " + classNameGenerator.generateName();
                case ROLL -> dice.roll(new DiceCombination(command.getArguments()[0]));
                case D20 -> "Rolled " + dice.d20Test(Integer.parseInt(command.getArguments()[0]),
                    D20State.valueOf(command.getArguments()[1])
                );
                case GENERATE_NAME -> "Generated name: " + characterNameGenerator.generateName();
                case GENERATE_ENCOUNTER -> encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]),
                    Integer.parseInt(command.getArguments()[1]), Integer.parseInt(command.getArguments()[2]),
                    ""
                );
                case GENERATE_ENCOUNTER_FILTER ->
                    encounter.start(EncounterDifficulty.valueOf(command.getArguments()[0]),
                        Integer.parseInt(command.getArguments()[1]),
                        Integer.parseInt(command.getArguments()[2]),
                        command.getArguments()[3]
                    );
                case ENCOUNTER_END -> encounter.end();
                case ATTACK -> encounter.attack(Integer.parseInt(command.getArguments()[0]),
                    Integer.parseInt(command.getArguments()[1]
                ));
                default -> "Unknown command, type help for help";
            };
        } catch (IllegalArgumentException e) {
            return "Unknown command, type help for help";
        }
    }
}
