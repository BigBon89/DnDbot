package org.example.gui;

import org.example.CommandHandler;
import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.ImGuiIO;
import imgui.ImFontConfig;
import imgui.ImVec2;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiFreeTypeBuilderFlags;
import imgui.app.Application;
import imgui.app.Configuration;

public class GUI extends Application {
    private final Windows windows;
    private final ImVec2 mainWindowSize;

    public GUI(CommandHandler commandHandler) {
        mainWindowSize = new ImVec2(800, 400);
        this.windows = new Windows(commandHandler, mainWindowSize);
    }

    @Override
    protected void configure(Configuration config) {
        config.setTitle("DnDbot");
        config.setWidth(Math.round(mainWindowSize.x));
        config.setHeight(Math.round(mainWindowSize.y));
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

    private void initColors(final ImGuiStyle style) {
        //style.setWindowRounding(5.0f);
        //style.setChildRounding(5.0f);
        style.setColor(ImGuiCol.WindowBg, 20.f / 255.f, 20.f / 255.f, 20.f / 255.f, 1.00f);
        style.setColor(ImGuiCol.ChildBg, 15.f / 255.f, 15.f / 255.f, 15.f / 255.f, 1.f);
        style.setColor(ImGuiCol.Button, 0.05f, 0.58f, 0.f, 1.f);
        style.setColor(ImGuiCol.ButtonHovered, 0.05f, 0.58f, 0.f, 1.f);
        style.setColor(ImGuiCol.ButtonActive, 0.05f, 0.58f, 0.f, 1.f);
        style.setColor(ImGuiCol.FrameBg, 40.f / 255.f, 40.f / 255.f, 40.f / 255.f, 1.00f);
        style.setColor(ImGuiCol.FrameBgActive, 40.f / 255.f, 40.f / 255.f, 40.f / 255.f, 1.00f);
        style.setColor(ImGuiCol.FrameBgHovered, 40.f / 255.f, 40.f / 255.f, 40.f / 255.f, 1.00f);
    }

    @Override
    protected void initImGui(final Configuration config) {
        super.initImGui(config);

        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);

        initColors(ImGui.getStyle());
        initFonts(io);
    }

    @Override
    public void process() {
        windows.renderMainWindow();
    }

    public static void start(CommandHandler commandHandler) {
        launch(new GUI(commandHandler));
    }
}