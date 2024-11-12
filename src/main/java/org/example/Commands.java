package org.example;

public enum Commands {
    HELP("help", 0, "help"),
    GENERATE_CITY("generate_city", 0, "generate_city"),
    GENERATE_CLASS("generate_class", 0, "generate_class"),
    ROLL("roll", 1, "roll 'formula'"),
    D20("d20", 2, "d20 'modifier' 'normal/advantage/disadvantage'"),
    GENERATE_NAME("generate_name", 0, "generate_name"),
    ENCOUNTER_START("encounter_start", 4, "encounter_start 'NORMAL/MEDIUM/HARD' 'PLAYERS COUNT' 'PLAYERS LEVEL' 'MONSTER FILTER'"),
    ENCOUNTER_END("encounter_end", 0, "encounter_end");

    private String commandName;
    private int argumentsCount;
    private String exampleForHelp;

    Commands(String commandName, int argumentsCount, String exampleForHelp) {
        this.commandName = commandName;
        this.argumentsCount = argumentsCount;
        this.exampleForHelp = exampleForHelp;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getArgumentsCount() {
        return argumentsCount;
    }

    public String getExampleForHelp() {
        return exampleForHelp;
    }

    public static Commands fromString(String command) {
        for (Commands type : Commands.values()) {
            if (type.getCommandName().equals(command)) {
                return type;
            }
        }
        return null;
    }

    public static String helpCommand() {
        StringBuilder helpMessage = new StringBuilder("Commands:\n");
        for (Commands command : Commands.values()) {
            helpMessage.append(command.getExampleForHelp()).append("\n");
        }
        return helpMessage.toString();
    }
}
