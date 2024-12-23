package org.example;

import org.example.gui.GUI;
import org.example.namegenerators.CharacterNameGenerator;
import org.example.namegenerators.CityNameGenerator;
import org.example.namegenerators.ClassNameGenerator;

public class BotLogicalCore {
    private final CommandHandler commandHandler;
    private final InputOutput ioHandler;

    public BotLogicalCore(CityNameGenerator cityNameGenerator,
        CharacterNameGenerator characterNameGenerator,
        ClassNameGenerator classNameGenerator,
        Dice dice,
        InputOutput inputOutput,
        Encounter encounter
    ) {
        this.ioHandler = inputOutput;
        this.commandHandler = new CommandHandler(
            cityNameGenerator,
            characterNameGenerator,
            classNameGenerator,
            dice,
            encounter
        );
    }

    public void startConsole() {
        while (true) {
            Command command = new Command(ioHandler.getText());
            String resultOutput = commandHandler.handleCommand(command);
            ioHandler.print(resultOutput);
        }
    }

    public void startGui() {
        GUI.start(commandHandler);
    }
}