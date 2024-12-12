package org.example;

public class Command {
    private final String command;
    private final String[] arguments;
    private final int argumentsCount;

    private final Commands commands;

    public Command(String inputLine) {
        String[] words = inputLine.split(" ");
        command = words[0];
        argumentsCount = words.length - 1;
        arguments = new String[argumentsCount];
        System.arraycopy(words, 1, arguments, 0, argumentsCount);

        commands = Commands.fromString(command);
    }

    String getCommand() {
        return command;
    }

    String[] getArguments() {
        return arguments;
    }

    int getArgumentsCount() {
        return argumentsCount;
    }

    boolean isValid() {
        return commands != null && argumentsCount >= commands.getArgumentsCount();
    }

    Commands getCommandType() {
        return commands;
    }
}
