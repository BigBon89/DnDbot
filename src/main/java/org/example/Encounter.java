package org.example;

import org.example.enums.EncounterDifficulty;

import java.util.Arrays;

public class Encounter {
    private boolean inEncount;
    private final Monsters monsters;
    private static final String[] allowedMonsterTypes = {
        "Angulotls", "Animals", "Basilisks", "Bugbears", "Chimeras", "Demons",
        "Devils", "Dragonets", "Elementals", "Giants", "Gibbering Mouthers",
        "Gnolls", "Goblins", "Griffons", "Hags", "Harpies", "Hellhounds", "Hobgoblins",
        "Humans", "Kobolds", "Lightbenders", "Lizardfolk", "Manticores", "Medusas",
        "Mimics", "Minotaur", "Ogres", "Olothec", "Orcs", "Otyughs", "Overminds",
        "Owlbears", "Shambling Mounds", "Stirges", "Time Raiders", "Treants", "Trolls",
        "Undead", "Valok", "Voiceless Talkers", "Wyverns", "Cave", "Enchanted Forest",
        "Graveyard and Tombs", "Road", "Ruined Keep", "Sewers", "Swamp", "Underground"
    };

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
                        Integer playersCount, Integer playersLevel,
                        String monsterFilter
    ) {
        if (inEncount) {
            return "The encounter has already started";
        }
        if (playersCount <= 0) {
            return "Invalid players count";
        }

        if (!Arrays.asList(allowedMonsterTypes).contains(monsterFilter) && !monsterFilter.isEmpty()) {
            return "Invalid monster filter";
        }
        monsters.generate(difficulty, playersCount, playersLevel, monsterFilter);

        if(monsters.getMonstersCount() == 0){
            return "Monsters are too powerful";
        }
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