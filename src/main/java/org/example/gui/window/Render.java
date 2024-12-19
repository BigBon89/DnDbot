package org.example.gui.window;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiStyleVar;

public abstract class Render {
    ImVec2 defaultWindowSize;

    protected void pushStyle() {
        float coefMultiplicate = 3.f;
        ImVec2 mainWindowSize = ImGui.getIO().getDisplaySize();
        float coefX = mainWindowSize.x / defaultWindowSize.x * coefMultiplicate;
        float coefY = mainWindowSize.y / defaultWindowSize.y * coefMultiplicate;

        ImGui.pushStyleVar(ImGuiStyleVar.FramePadding,
            ImGui.getStyle().getFramePaddingX() * coefX,
            ImGui.getStyle().getFramePaddingY() * coefY
        );
    }

    protected void popStyle() {
        ImGui.popStyleVar(1);
    }

    public abstract void render();
}
