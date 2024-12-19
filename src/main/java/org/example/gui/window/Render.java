package org.example.gui.window;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiStyleVar;

public abstract class Render {
    ImVec2 defaultWindowSize;

    protected void pushStyle() {
        float COEF_MULTIPLICATE = 3.f;
        ImVec2 mainWindowSize = ImGui.getIO().getDisplaySize();
        float COEF_X = mainWindowSize.x / 800.f * COEF_MULTIPLICATE;
        float COEF_Y = mainWindowSize.y / 600.f * COEF_MULTIPLICATE;

        ImGui.pushStyleVar(ImGuiStyleVar.FramePadding,
            ImGui.getStyle().getFramePaddingX() * COEF_X,
            ImGui.getStyle().getFramePaddingY() * COEF_Y
        );
    }

    protected void popStyle() {
        ImGui.popStyleVar(1);
    }

    public abstract void render();
}
