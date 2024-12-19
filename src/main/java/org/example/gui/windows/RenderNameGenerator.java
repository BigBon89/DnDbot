package org.example.gui.windows;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiStyleVar;
import org.example.Command;
import org.example.CommandHandler;

public class RenderNameGenerator extends Render {

    private final CommandHandler commandHandler;

    private String generateCityResult;
    private String generateClassResult;
    private String generateNameResult;

    public RenderNameGenerator(CommandHandler commandHandler, ImVec2 defaultWindowSize) {
        this.commandHandler = commandHandler;

        this.defaultWindowSize = defaultWindowSize;

        generateCityResult = "";
        generateClassResult = "";
        generateNameResult = "";
    }

    public void render() {
        pushStyle();
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
        popStyle();
    }
}
