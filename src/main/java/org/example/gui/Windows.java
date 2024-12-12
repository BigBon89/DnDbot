package org.example.gui;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImInt;
import imgui.type.ImString;
import org.example.Command;
import org.example.CommandHandler;
import org.example.enums.D20State;
import org.example.enums.EncounterDifficulty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Windows {
    private final CommandHandler commandHandler;

    private final ImVec2 mainWindowSize;

    private String generateCityResult;
    private String generateClassResult;
    private String generateNameResult;

    private final ImInt currentD20TestModifier;
    private final ImInt currentD20State;
    private String currentD20TestResult;
    private final ImString currentDiceFormula;
    private String currentRollResult;

    private final ImInt currentDamage;
    private final ImInt currentDifficulty;
    private final ImInt currentPlayersCount;
    private final ImInt currentPlayersLevel;
    private final ImString currentMonsterFilter;
    private String[] currentMonstersBuffer;

    public Windows(CommandHandler commandHandler, ImVec2 mainWindowSize) {
        this.commandHandler = commandHandler;

        this.mainWindowSize = mainWindowSize;

        generateCityResult = "";
        generateClassResult = "";
        generateNameResult = "";

        currentD20TestModifier = new ImInt(0);
        currentD20State = new ImInt(0);
        currentD20TestResult = "";
        currentDiceFormula = new ImString("", 32);
        currentRollResult = "";

        currentDamage = new ImInt(1);
        currentDifficulty = new ImInt(0);
        currentPlayersCount = new ImInt(4);
        currentPlayersLevel = new ImInt(4);
        currentMonsterFilter = new ImString("", 32);
        currentMonstersBuffer = new String[0];
    }

    private void renderNamesGenerators() {
        if (ImGui.button("Generate City", new ImVec2(120, 20))) {
            generateCityResult = commandHandler.handleCommand(new Command("generate_city"));
        }
        ImGui.sameLine();
        ImGui.text(generateCityResult);

        if (ImGui.button("Generate Class", new ImVec2(120, 20))) {
            generateClassResult = commandHandler.handleCommand(new Command("generate_class"));
        }
        ImGui.sameLine();
        ImGui.text(generateClassResult);

        if (ImGui.button("Generate Name", new ImVec2(120, 20))) {
            generateNameResult = commandHandler.handleCommand(new Command("generate_name"));
        }
        ImGui.sameLine();
        ImGui.text(generateNameResult);
    }

    private void renderDice() {
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
    }

    private void renderEncounterSettings() {
        String[] difficulties = EncounterDifficulty.getStringValues();
        ImGui.combo("Difficulty", currentDifficulty, difficulties);
        if (ImGui.inputInt("Players Count", currentPlayersCount)) {
            if (currentPlayersCount.get() <= 0) {
                currentPlayersCount.set(1);
            }
        }
        if (ImGui.inputInt("Players Level", currentPlayersLevel)) {
            if (currentPlayersLevel.get() > 20) {
                currentPlayersLevel.set(20);
            } else if (currentPlayersLevel.get() <= 0) {
                currentPlayersLevel.set(1);
            }
        }
        ImGui.inputText("Monster Filter", currentMonsterFilter);

        if (ImGui.button("Start Encounter", new ImVec2(110, 20))) {
            if (currentMonstersBuffer.length == 0) {
                String result = "";
                if (currentMonsterFilter.isEmpty()) {
                    result = commandHandler.handleCommand(new Command("generate_encounter "
                            + difficulties[currentDifficulty.getData()[0]]
                            + " "
                            + currentPlayersLevel
                            + " "
                            + currentPlayersLevel
                    ));
                } else {
                    result = commandHandler.handleCommand(new Command("generate_encounter_filter "
                            + difficulties[currentDifficulty.getData()[0]]
                            + " "
                            + currentPlayersLevel
                            + " "
                            + currentPlayersLevel
                            + " "
                            + currentMonsterFilter
                    ));
                }

                currentMonstersBuffer = result.split("\n");
            }
        }

        if (ImGui.button("End Encounter", new ImVec2(110, 20))) {
            if (currentMonstersBuffer.length != 0) {
                commandHandler.handleCommand(new Command("encounter_end"));
                currentMonstersBuffer = new String[0];
            }
        }
    }

    private void renderEncounter() {
        if (currentMonstersBuffer.length == 0) {
            return;
        }

        if (currentMonstersBuffer[0].equals("Invalid monster filter")) {
            currentMonstersBuffer = new String[0];
            return;
        }

        if (ImGui.inputInt("Damage", currentDamage)) {
            if (currentDamage.get() <= 0) {
                currentDamage.set(1);
            }
        }
        Pattern pattern = Pattern.compile("^(\\d+)\\.");
        for (int i = 0; i < currentMonstersBuffer.length; i++) {
            if (ImGui.button("Attack##" + i)) {
                Matcher matcher = pattern.matcher(currentMonstersBuffer[i]);

                if (matcher.find()) {
                    String monsterIndex = matcher.group(1);
                    String result = commandHandler.handleCommand(new Command("attack "
                            + monsterIndex
                            + " "
                            + currentDamage
                    ));
                    currentMonstersBuffer = result.split("\n");
                    if (currentMonstersBuffer[0].equals("All monsters is dead")) {
                        currentMonstersBuffer = new String[0];
                    }
                }
            } else {
                ImGui.sameLine();
                ImGui.text(currentMonstersBuffer[i]);
            }
        }
    }

    private void renderLeftWindow() {
        ImGui.beginChild("leftWindow", (mainWindowSize.x - 24) / 2, mainWindowSize.y - 16, true);

        renderNamesGenerators();

        ImGui.separator();

        renderDice();

        ImGui.endChild();
    }

    private void renderRightWindow() {
        ImGui.beginChild("rightWindow", (mainWindowSize.x - 24) / 2, mainWindowSize.y - 16, true);

        renderEncounterSettings();

        ImGui.separator();

        renderEncounter();

        ImGui.endChild();
    }

    public void renderMainWindow() {
        //Расстояние между чайлдами = 8
        ImGui.setNextWindowSize(mainWindowSize);
        ImGui.setNextWindowPos(0, 0);
        ImGui.begin("DnDbot",
                null,
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar
        );

        renderLeftWindow();

        ImGui.sameLine();

        renderRightWindow();

        ImGui.end();
    }
}
