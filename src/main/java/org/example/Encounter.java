package org.example;

public class Encounter {
    private boolean inEncount;
    private Monsters monsters;

    public Encounter() {
        inEncount = false;
        monsters = new Monsters();
    }

    public Encounter(int seed) {
        inEncount = false;
        monsters = new Monsters(seed);
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

        if (monsterIndex > monsters.getMonstersCount())
            return "Wrong monster index";

        if (!monsters.getMonsterByIndex(monsterIndex).isAlive)
            return "Monster already dead, choose another monster";

        monsters.damage(monsterIndex, damage);

        for (int i = 0; i < monsters.getMonstersCount(); i++) {
            if (monsters.getMonsterByIndex(i).isAlive)
                return printMonsters();
        }
        return printMonsters() + "\nAll monsters is dead\n" + end();
    }
}
