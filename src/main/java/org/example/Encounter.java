package org.example;

public class Encounter {
    private boolean inEncount;

    public Encounter() {
        inEncount = false;
    }

    public String Start(EncounterDifficulty difficulty, Integer playersCount, Integer playersLevel, String monsterFilter) {
        if (inEncount)
            return "The encounter has already started";

        Monsters yes = new Monsters();
        yes.generate(difficulty, playersCount, playersLevel, monsterFilter);

        //inEncount = true;
        return  yes.print();
        //return "Encounter Started";
    }

    public String End() {
        if (!inEncount)
            return "The encounter has not started";

        inEncount = false;
        return "The encounter is over";
    }
}
