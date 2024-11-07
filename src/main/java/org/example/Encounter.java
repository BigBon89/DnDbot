package org.example;

public class Encounter {
    private boolean inEncount;

    public Encounter() {
        inEncount = false;
    }

    public String Start() {
        inEncount = true;
        return "Encount Started";
    }
}
