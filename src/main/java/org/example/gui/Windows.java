package org.example.gui;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import org.example.CommandHandler;
import org.example.gui.windows.RenderDice;
import org.example.gui.windows.RenderEncounter;
import org.example.gui.windows.RenderNameGenerator;

public class Windows {
    private static final float SPACING_X = 8;

    private ImVec2 mainWindowSize;

    private final RenderNameGenerator renderNameGenerator;
    private final RenderDice renderDice;
    private final RenderEncounter renderEncounter;

    public Windows(CommandHandler commandHandler, ImVec2 mainWindowSize) {

        this.mainWindowSize = mainWindowSize;

        renderNameGenerator = new RenderNameGenerator(commandHandler);
        renderDice = new RenderDice(commandHandler);
        renderEncounter = new RenderEncounter(commandHandler);
    }

    private void renderLeftWindow() {
        ImGui.beginChild("leftWindow", (mainWindowSize.x - SPACING_X * 3) / 2, mainWindowSize.y - SPACING_X * 2, true);

        renderNameGenerator.render();

        ImGui.separator();

        renderDice.render();

        ImGui.endChild();
    }

    private void renderRightWindow() {
        ImGui.beginChild("rightWindow", (mainWindowSize.x - SPACING_X * 3) / 2, mainWindowSize.y - SPACING_X * 2, true);

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
