package org.example;

public class Command {
    private String command;
    private String[] arguments;
    private int argumentsCount;

    Command(String inputLine) {
        String[] words = inputLine.split(" ");
        command = words[0];
        argumentsCount = words.length - 1;
        arguments = new String[argumentsCount];
        System.arraycopy(words, 1, arguments, 0, argumentsCount);
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
}
