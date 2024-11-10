package org.example;

import java.util.Random;

public class Monsters {
    Monster[] monsters;

    protected Random random;
    public Monsters() {random = new Random();}
    public Monsters(int seed) {random = new Random(seed);}

    int[] easyCRs = {1, 1, 2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68};
    int[] normalCRs = {1, 2, 4, 6, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72};
    int[] hardCRs = {2, 4, 6, 8, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80};
    int[] CRCaps = {8, 24, 32, 48, 64, 72, 80, 96, 104, 120, 128, 136, 152, 160, 176, 192, 200, 208, 224, 240};

    public void Generate(EncounterDifficulty difficulty, Integer playersCount, Integer playersLevel, String monsterFilter) {
        monsters = new Monster[1]; //TODO: случайно заполнить
        int CRBudget = 0;
        int CRCap = CRCaps[playersLevel]*8;
        switch (difficulty){
            case EASY -> CRBudget = easyCRs[playersLevel] * playersCount;
            case NORMAL -> CRBudget = normalCRs[playersLevel] * playersCount;
            case HARD -> CRBudget = hardCRs[playersLevel] * playersCount;
        }

        int numberOfUniqueMonsters = random.nextInt(4) + 1  + (CRBudget/CRCap);
        int[] uniqueMonstersCR = new int[numberOfUniqueMonsters];
        int minCR = (CRBudget - CRCap) / (numberOfUniqueMonsters - 1);
        int maxCR = CRBudget / numberOfUniqueMonsters;
        for (int i = 0; i < numberOfUniqueMonsters; i++) {
            uniqueMonstersCR[i] = random.nextInt(maxCR - minCR + 1) + minCR;
        }

    }

    public void Print() {

    }

    public void Attack(Integer monsterIndex) {

    }
}
