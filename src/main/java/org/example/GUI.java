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

public class GUI extends Application {
    private final CommandHandler commandHandler;
    private String generateCityResult;
    private String generateClassResult;
    private String generateNameResult;

    private final ImInt damage;
    private final ImInt currentDifficult;

    private final ImVec2 windowSize;

    public GUI(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        generateCityResult = "";
        generateClassResult = "";
        generateNameResult = "";
        damage = new ImInt();
        currentDifficult = new ImInt(0);
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
        ImGui.beginChild("window1", (windowSize.x - 24) / 2, 230, true);

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

        ImGui.endChild();

        ImGui.sameLine();

        ImGui.beginChild("window2", (windowSize.x - 24) / 2, 230, true);

        String[] difficulties = {"NORMAL", "MEDIUM", "HARD"};
        ImGui.combo("difficult", currentDifficult, difficulties);
        if (ImGui.button("Start Encounter")) {
            commandHandler.handleCommand(new Command("generate_encounter" + difficulties[currentDifficult.getData()[0]]));
        }

        ImGui.inputInt("Damage", damage);
        for (int i = 0; i < 5; i++) {
            if (ImGui.button("Attack##" + i)) {
                commandHandler.handleCommand(new Command("attack " + i + " " + damage));
            }
            ImGui.sameLine();
            ImGui.text(i + ". Kobold Veles 1/1");
        }
        ImGui.endChild();
        ImGui.end();
    }

    public static void start(CommandHandler commandHandler) {
        launch(new GUI(commandHandler));
    }
}