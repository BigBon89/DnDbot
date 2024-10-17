package org.example;

public class Command {
    private String command;
    private String[] arguments;
    private int argumentsCount;

    Command(String inputLine) {
        String[] words = inputLine.split(" ");
        command = words[0];
        arguments = new String[words.length - 1];
        argumentsCount = words.length - 1;
        for (int i = 0; i < argumentsCount; i++){
            arguments[i] = words[i+1];
        }
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
