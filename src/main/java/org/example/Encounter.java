package org.example;

public class Encounter {
    private boolean inEncount;

    public Encounter() {
        inEncount = false;
    }

    public String Start() {
        if (inEncount)
            return "The encounter has already started";

        inEncount = true;
        return "Encounter Started";
    }

    public String End() {
        if (!inEncount)
            return "The encounter has not started";

        inEncount = false;
        return "The encounter is over";
    }
}
