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

public class GUI extends Application {
    private final CommandHandler commandHandler;
    private String generateCityResult;
    private String generateClassResult;
    private String generateNameResult;


    private final ImInt d20TestModifier;
    private String d20TestResult;
    private final ImString diceFormula;
    private String rollResult;

    private final ImInt currentDamage;
    private final ImInt currentDifficult;
    private final ImInt currentPlayersCount;
    private final ImInt currentPlayersLevel;
    private final ImString currentFilter;
    private String[] currentMonsters;

    private final ImVec2 windowSize;

    public GUI(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        generateCityResult = "";
        generateClassResult = "";
        generateNameResult = "";
        d20TestModifier = new ImInt();
        d20TestResult = "";
        diceFormula = new ImString();
        rollResult = "";
        currentDamage = new ImInt();
        currentDifficult = new ImInt(0);
        currentPlayersCount = new ImInt(1);
        currentPlayersLevel = new ImInt(1);
        currentFilter = new ImString();
        currentMonsters = new String[0];
        windowSize = new ImVec2(800, 500);
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
        //Расстояние между чайлдами = 8
        ImGui.setNextWindowSize(windowSize);
        ImGui.setNextWindowPos(0, 0);
        ImGui.begin("DnDbot",
                null,
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar
        );
        ImGui.beginChild("window1", (windowSize.x - 24) / 2, windowSize.y - 20, true);

        if (ImGui.button("Generate City")) {
            generateCityResult = commandHandler.handleCommand(new Command("generate_city"));
        }
        ImGui.sameLine();
        ImGui.text(generateCityResult);

        if (ImGui.button("Generate Class")) {
            generateClassResult = commandHandler.handleCommand(new Command("generate_class"));
        }
        ImGui.sameLine();
        ImGui.text(generateClassResult);

        if (ImGui.button("Generate Name")) {
            generateNameResult = commandHandler.handleCommand(new Command("generate_name"));
        }
        ImGui.sameLine();
        ImGui.text(generateNameResult);

        ImGui.inputText("Dice formula", diceFormula);
        if (ImGui.button("Roll dice")) {
            rollResult = commandHandler.handleCommand(new Command("roll " + diceFormula));
        }
        ImGui.sameLine();
        ImGui.text(rollResult);

        ImGui.endChild();

        ImGui.sameLine();

        ImGui.beginChild("window2", (windowSize.x - 24) / 2, windowSize.y - 20, true);

        String[] difficulties = {"NORMAL", "MEDIUM", "HARD"};
        ImGui.combo("Difficult", currentDifficult, difficulties);
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
        ImGui.inputText("Monster Filter", currentFilter);

        if (ImGui.button("Start Encounter")) {
            if (currentMonsters.length == 0) {
                String result = commandHandler.handleCommand(new Command("generate_encounter " + difficulties[currentDifficult.getData()[0]] + " " + currentPlayersLevel + " " + currentPlayersLevel));
                currentMonsters = result.split("\n");
            }
        }

        if (ImGui.button("End Encounter")) {
            if (currentMonsters.length != 0) {
                commandHandler.handleCommand(new Command("encounter_end"));
                currentMonsters = new String[0];
            }
        }

        if (currentMonsters.length != 0) {
            if (ImGui.inputInt("Damage", currentDamage)) {
                if (currentDamage.get() <= 0) {
                    currentDamage.set(1);
                }
            }
        }
        for (int i = 0; i < currentMonsters.length; i++) {
            if (ImGui.button("Attack##" + i)) {
                //TODO: при индексе 10 краш т.к. берет 1
                String result = commandHandler.handleCommand(new Command("attack " + currentMonsters[i].charAt(0) + " " + currentDamage));
                currentMonsters = result.split("\n");
            }
            else {
                //TODO: fix index
                ImGui.sameLine();
                ImGui.text(currentMonsters[i]);
            }
        }

        ImGui.endChild();
        ImGui.end();
    }

    public static void start(CommandHandler commandHandler) {
        launch(new GUI(commandHandler));
    }
}