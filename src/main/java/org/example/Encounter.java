package org.example;

public class Encounter {
    private boolean inEncount;
    private Monsters monsters;

    public Encounter() {
        inEncount = false;
        monsters = new Monsters();
    }

    public String printMonsters() {
        return monsters.print();
    }

    public String start(EncounterDifficulty difficulty, Integer playersCount, Integer playersLevel, String monsterFilter) {
        if (inEncount)
            return "The encounter has already started";

        monsters.generate(difficulty, playersCount, playersLevel, monsterFilter);

        inEncount = true;
        return printMonsters();
    }

    public String end() {
        if (!inEncount)
            return "The encounter has not started";

        inEncount = false;
        return "The encounter is over";
    }

    public String attack(int monsterIndex, int damage) {
        if (!inEncount)
            return "The encounter has not started";

        monsters.damage(monsterIndex, damage);
        return printMonsters();
    }
}
