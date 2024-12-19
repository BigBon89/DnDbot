package org.example.gui.windows;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.type.ImInt;
import imgui.type.ImString;
import org.example.Command;
import org.example.CommandHandler;
import org.example.enums.D20State;

public class RenderDice extends Render {
    private final CommandHandler commandHandler;

    private final ImInt currentD20TestModifier;
    private final ImInt currentD20State;
    private String currentD20TestResult;
    private final ImString currentDiceFormula;
    private String currentRollResult;

    public RenderDice(CommandHandler commandHandler, ImVec2 defaultWindowSize) {
        this.commandHandler = commandHandler;

        this.defaultWindowSize = defaultWindowSize;

        currentD20TestModifier = new ImInt(0);
        currentD20State = new ImInt(0);
        currentD20TestResult = "";
        currentDiceFormula = new ImString("", 32);
        currentRollResult = "";
    }

    public void render() {
        pushStyle();
        ImGui.inputInt("Modifier", currentD20TestModifier);
        String[] d20States = D20State.getStringValues();
        ImGui.combo("D20 State", currentD20State, d20States);

        if (ImGui.button("Roll d20")) {
            currentD20TestResult = commandHandler.handleCommand(new Command("d20 "
                    + currentD20TestModifier
                    + " "
                    + d20States[currentD20State.get()]
            ));
        }
        ImGui.sameLine();
        ImGui.text(currentD20TestResult);

        ImGui.inputText("Dice formula", currentDiceFormula);
        if (ImGui.button("Roll dice")) {
            if (!currentDiceFormula.isEmpty()) {
                currentRollResult = commandHandler.handleCommand(new Command("roll " + currentDiceFormula));
            }
        }
        ImGui.sameLine();
        ImGui.text(currentRollResult);
        popStyle();
    }
}
