package org.example.gui.windows;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.type.ImInt;
import org.example.Command;
import org.example.CommandHandler;
import org.example.Encounter;
import org.example.enums.EncounterDifficulty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenderEncounter extends Render {
    private final CommandHandler commandHandler;

    private final ImInt currentDamage;
    private final ImInt currentDifficulty;
    private final ImInt currentPlayersCount;
    private final ImInt currentPlayersLevel;
    private final ImInt currentMonsterFilter;
    private String[] currentMonstersBuffer;

    private final String[] allowedMonsterTypesForComboBox;
    private String encounterErrorMessage;

    public RenderEncounter(CommandHandler commandHandler, ImVec2 defaultWindowSize) {
        this.commandHandler = commandHandler;

        this.defaultWindowSize = defaultWindowSize;

        currentDamage = new ImInt(1);
        currentDifficulty = new ImInt(0);
        currentPlayersCount = new ImInt(4);
        currentPlayersLevel = new ImInt(4);
        currentMonsterFilter = new ImInt(0);
        currentMonstersBuffer = new String[0];

        encounterErrorMessage = "";

        String[] originalTypes = Encounter.getAllowedMonsterTypes();
        allowedMonsterTypesForComboBox = new String[originalTypes.length + 1];
        allowedMonsterTypesForComboBox[0] = "None";
        System.arraycopy(originalTypes, 0, allowedMonsterTypesForComboBox, 1, originalTypes.length);
    }

    public void render() {
        pushStyle();
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

        ImGui.combo("Monster Filter", currentMonsterFilter, allowedMonsterTypesForComboBox);
        if (ImGui.button("Start Encounter")) {
            if (currentMonstersBuffer.length == 0) {
                String result = commandHandler.handleCommand(new Command(
                    String.format(
                        "%s %s %d %d %s",
                        currentMonsterFilter.get() == 0 ? "generate_encounter" : "generate_encounter_filter",
                        difficulties[currentDifficulty.getData()[0]],
                        currentPlayersCount.get(),
                        currentPlayersLevel.get(),
                        currentMonsterFilter.get() == 0 ? "" : allowedMonsterTypesForComboBox[
                            currentMonsterFilter.get()
                        ]
                    )
                ));

                currentMonstersBuffer = result.split("\n");
            }
            encounterErrorMessage = "";
        }

        ImGui.sameLine();
        ImGui.text(encounterErrorMessage);

        if (ImGui.button("End Encounter")) {
            if (currentMonstersBuffer.length != 0) {
                commandHandler.handleCommand(new Command("encounter_end"));
                currentMonstersBuffer = new String[0];
            }
        }

        ImGui.separator();

        if (currentMonstersBuffer.length == 0) {
            popStyle();
            return;
        }

        if (currentMonstersBuffer[0].equals("Monsters are too powerful")) {
            encounterErrorMessage = "Monsters are too powerful";
            currentMonstersBuffer = new String[0];
            popStyle();
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
        popStyle();
    }
}
