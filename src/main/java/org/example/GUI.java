package org.example;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.type.ImInt;

public class GUI extends Application {
    BotLogicalCore botLogicalCore = new BotLogicalCore(
            new CityNameGenerator(),
            new CharacterNameGenerator(),
            new ClassNameGenerator(),
            new Dice(),
            new Console(),
            new Encounter()
    );

    @Override
    protected void configure(Configuration config) {
        config.setTitle("DnDbot");
    }

    private final ImInt damage = new ImInt();
    @Override
    public void process() {
        ImGui.setNextWindowSize(500, 500);
        ImGui.begin("DnDbot");
        ImGui.beginChild("w1", 230, 230, true);
        if (ImGui.button("Generate City")) {

        }
        ImGui.sameLine();
        ImGui.text("Result");

        if (ImGui.button("Generate Class")) {

        }
        ImGui.sameLine();
        ImGui.text("Result");

        if (ImGui.button("Generate Name")) {

        }
        ImGui.sameLine();
        ImGui.text("Result");
        ImGui.endChild();
        ImGui.sameLine();
        ImGui.beginChild("w2", 230, 230, true);
        ImGui.inputInt("Damage", damage);
        for (int i = 0; i < 5; i++) {
            if (ImGui.button("Attack##" + i)) {
                //attack
            }
            ImGui.sameLine();
            ImGui.text(i + ". Kobold Veles 1/1");
        }
        ImGui.endChild();
        ImGui.end();
    }

    public static void start() {
        launch(new GUI());
    }
}