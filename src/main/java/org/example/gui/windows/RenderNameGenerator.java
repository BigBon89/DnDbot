package org.example.gui.windows;

import imgui.ImGui;
import imgui.ImVec2;
import org.example.Command;
import org.example.CommandHandler;

public class RenderNameGenerator {
    private static final float BUTTON_GENERATOR_SIZE_X = 120;
    private static final float BUTTON_SIZE_Y = 20;

    private final CommandHandler commandHandler;

    private String generateCityResult;
    private String generateClassResult;
    private String generateNameResult;

    public RenderNameGenerator(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;

        generateCityResult = "";
        generateClassResult = "";
        generateNameResult = "";

    }

    public void render() {
        if (ImGui.button("Generate City", new ImVec2(BUTTON_GENERATOR_SIZE_X, BUTTON_SIZE_Y))) {
            generateCityResult = commandHandler.handleCommand(new Command("generate_city"));
        }
        ImGui.sameLine();
        ImGui.text(generateCityResult);

        if (ImGui.button("Generate Class", new ImVec2(BUTTON_GENERATOR_SIZE_X, BUTTON_SIZE_Y))) {
            generateClassResult = commandHandler.handleCommand(new Command("generate_class"));
        }
        ImGui.sameLine();
        ImGui.text(generateClassResult);

        if (ImGui.button("Generate Name", new ImVec2(BUTTON_GENERATOR_SIZE_X, BUTTON_SIZE_Y))) {
            generateNameResult = commandHandler.handleCommand(new Command("generate_name"));
        }
        ImGui.sameLine();
        ImGui.text(generateNameResult);
    }
}
