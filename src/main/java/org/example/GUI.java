package org.example;

import imgui.ImFontConfig;
import imgui.ImVec2;
import imgui.flag.ImGuiFreeTypeBuilderFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.type.ImInt;
import imgui.type.ImString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends Application {
    private final CommandHandler commandHandler;

    private final ImVec2 windowSize;

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

    public GUI(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;

        windowSize = new ImVec2(800, 400);

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

    private void renderLeftWindow() {
        ImGui.beginChild("window1", (windowSize.x - 24) / 2, windowSize.y - 16, true);

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

        ImGui.separator();

        ImGui.inputInt("Modifier", currentD20TestModifier);
        String[] d20States = {"NORMAL", "ADVANTAGE", "DISADVANTAGE"};
        ImGui.combo("D20 State", currentD20State, d20States);

        if (ImGui.button("Roll d20")) {
            currentD20TestResult = commandHandler.handleCommand(new Command("d20 " + currentD20TestModifier + " " + d20States[currentD20State.getData()[0]]));
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

        ImGui.endChild();
    }

    private void renderEncounterWindow() {
        ImGui.beginChild("window2", (windowSize.x - 24) / 2, windowSize.y - 16, true);

        String[] difficulties = {"NORMAL", "MEDIUM", "HARD"};
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

        if (ImGui.button("Start Encounter")) {
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

        if (ImGui.button("End Encounter")) {
            if (currentMonstersBuffer.length != 0) {
                commandHandler.handleCommand(new Command("encounter_end"));
                currentMonstersBuffer = new String[0];
            }
        }

        ImGui.separator();

        if (currentMonstersBuffer.length != 0) {
            if (ImGui.inputInt("Damage", currentDamage)) {
                if (currentDamage.get() <= 0) {
                    currentDamage.set(1);
                }
            }
        }
        Pattern pattern = Pattern.compile("^(\\d+)\\.");
        for (int i = 0; i < currentMonstersBuffer.length; i++) {
            if (ImGui.button("Attack##" + i)) {
                Matcher matcher = pattern.matcher(currentMonstersBuffer[i]);

                if (matcher.find()) {
                    String monsterIndex = matcher.group(1);
                    String result = commandHandler.handleCommand(new Command("attack " + monsterIndex + " " + currentDamage));
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

        ImGui.endChild();
    }

    private void renderMainWindow() {
        //Расстояние между чайлдами = 8
        ImGui.setNextWindowSize(windowSize);
        ImGui.setNextWindowPos(0, 0);
        ImGui.begin("DnDbot",
                null,
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar
        );

        renderLeftWindow();

        ImGui.sameLine();

        renderEncounterWindow();

        ImGui.end();
    }

    @Override
    protected void configure(Configuration config) {
        config.setTitle("DnDbot");
        config.setWidth(Math.round(windowSize.x));
        config.setHeight(Math.round(windowSize.y));
    }

    private void initFonts(final ImGuiIO io) {
        io.getFonts().setFreeTypeRenderer(true);

        final ImFontConfig fontConfig = new ImFontConfig();

        fontConfig.setFontBuilderFlags(ImGuiFreeTypeBuilderFlags.MonoHinting
                | ImGuiFreeTypeBuilderFlags.Monochrome
        );

        io.getFonts().addFontFromFileTTF("C:/Windows/Fonts/verdana.ttf", 14, fontConfig);

        fontConfig.setMergeMode(true);

        io.getFonts().build();

        fontConfig.destroy();
    }

    @Override
    protected void initImGui(final Configuration config) {
        super.initImGui(config);

        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);

        initFonts(io);
    }

    @Override
    public void process() {
        renderMainWindow();
    }

    public static void start(CommandHandler commandHandler) {
        launch(new GUI(commandHandler));
    }
}