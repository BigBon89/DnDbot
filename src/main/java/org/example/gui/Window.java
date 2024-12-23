package org.example.gui;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import org.example.CommandHandler;
import org.example.gui.window.RenderDice;
import org.example.gui.window.RenderEncounter;
import org.example.gui.window.RenderNameGenerator;

public class Window {
    private static final float SPACING_X = 8;
    private static final float SPACING_Y = 8;
    private static final float WINDOW_PADDING = 2 * SPACING_X;
    private static final float HALF_WIDTH_RATIO = 0.5f;

    private ImVec2 mainWindowSize;

    private final RenderNameGenerator renderNameGenerator;
    private final RenderDice renderDice;
    private final RenderEncounter renderEncounter;

    public Window(CommandHandler commandHandler, ImVec2 mainWindowSize) {

        this.mainWindowSize = mainWindowSize;

        renderNameGenerator = new RenderNameGenerator(commandHandler, mainWindowSize);
        renderDice = new RenderDice(commandHandler, mainWindowSize);
        renderEncounter = new RenderEncounter(commandHandler, mainWindowSize);
    }

    private void renderLeftWindow() {
        float windowWidth = (mainWindowSize.x - WINDOW_PADDING - SPACING_X) * HALF_WIDTH_RATIO;
        float windowHeight = mainWindowSize.y - WINDOW_PADDING;

        ImGui.beginChild("leftWindow", windowWidth, windowHeight, true);

        renderNameGenerator.render();

        ImGui.separator();

        renderDice.render();

        ImGui.endChild();
    }

    private void renderRightWindow() {
        float windowWidth = (mainWindowSize.x - WINDOW_PADDING - SPACING_X) * HALF_WIDTH_RATIO;
        float windowHeight = mainWindowSize.y - WINDOW_PADDING;

        ImGui.beginChild("rightWindow", windowWidth, windowHeight, true);

        renderEncounter.render();

        ImGui.endChild();
    }

    public void renderMainWindow() {
        mainWindowSize = ImGui.getIO().getDisplaySize();

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
