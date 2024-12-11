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

    public String start(EncounterDifficulty difficulty,
                        Integer playersCount,
                        Integer playersLevel,
                        String monsterFilter
    ) {
        if (inEncount) {
            return "The encounter has already started";
        }

        if (playersCount <= 0) {
            return "Invalid players count";
        }

        monsters.generate(difficulty, playersCount, playersLevel, monsterFilter);

        inEncount = true;
        return printMonsters();
    }

    public String end() {
        if (!inEncount) {
            return "The encounter has not started";
        }

        inEncount = false;
        return "The encounter is over";
    }

    public String attack(int monsterIndex, int damage) {
        if (!inEncount) {
            return "The encounter has not started";
        }

        if (damage <= 0) {
            return "Damage must be a positive number";
        }

        if (!monsters.isValidIndex(monsterIndex)) {
            return "Wrong monster index";
        }

        monsters.damage(monsterIndex, damage);

        if (monsters.getMonstersCount() != 0) {
            return printMonsters();
        }

        return "All monsters is dead\n" + end();
    }
}
