package org.example;

import java.util.*;

public class Monsters {
    Monster[] monsters;
    int[] uniqueMonstersTotalCR;
    int[] uniqueMonstersCR;

    protected Random random;
    public Monsters() {random = new Random();}
    public Monsters(int seed) {random = new Random(seed);}

    Set<Integer> allowedCRs = new HashSet<>(Arrays.asList(1, 2, 4, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120, 128, 136, 144, 152, 160, 168, 184));
    int[] easyCRs = {1, 1, 2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68};
    int[] normalCRs = {1, 2, 4, 6, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72};
    int[] hardCRs = {2, 4, 6, 8, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80};
    int[] CRCaps = {8, 24, 32, 48, 64, 72, 80, 96, 104, 120, 128, 136, 152, 160, 176, 192, 200, 208, 224, 240};

    public void Generate(EncounterDifficulty difficulty, Integer playersCount, Integer playersLevel, String monsterFilter) {
        monsters = new Monster[1]; //TODO: случайно заполнить
        int CRBudget = 0;
        int CRCap = CRCaps[playersLevel-1];
        switch (difficulty){
            case EASY -> CRBudget = easyCRs[playersLevel-1] * playersCount;
            case NORMAL -> CRBudget = normalCRs[playersLevel-1] * playersCount;
            case HARD -> CRBudget = hardCRs[playersLevel-1] * playersCount;
        }

        int numberOfUniqueMonsters = Math.min(random.nextInt(4) + 1, CRBudget);
        uniqueMonstersTotalCR = new int[numberOfUniqueMonsters];
        uniqueMonstersCR = new int[numberOfUniqueMonsters];

        int randomStep;
        if(playersLevel<3) {
            randomStep = 1;
        }else if(playersLevel<4){
            randomStep = Math.min(2, CRBudget/2);
        } else if (playersLevel<5) {
            randomStep = Math.min(4, CRBudget/4);
        }else {
            randomStep = Math.min(8, CRBudget/8);
            CRBudget = CRBudget - CRBudget%8;
        }

        int minCR = randomStep;
        int maxCR = CRBudget / Math.max(numberOfUniqueMonsters - 1, 1);
        for (int i = 0; i < numberOfUniqueMonsters - 1; i++) {
            uniqueMonstersTotalCR[i] = random.nextInt(Math.min(maxCR, CRBudget) - minCR + 1) + minCR;
            uniqueMonstersTotalCR[i] = uniqueMonstersTotalCR[i] - uniqueMonstersTotalCR[i]%randomStep;
            CRBudget -= uniqueMonstersTotalCR[i];
        }
        uniqueMonstersTotalCR[numberOfUniqueMonsters - 1] = CRBudget;

        if(uniqueMonstersTotalCR[numberOfUniqueMonsters - 1] == 0){
            int previousCR = uniqueMonstersTotalCR[numberOfUniqueMonsters - 2];
            uniqueMonstersTotalCR[numberOfUniqueMonsters - 1] = previousCR/2 - previousCR % randomStep;
            uniqueMonstersTotalCR[numberOfUniqueMonsters - 2] = previousCR/2 + (randomStep - previousCR) % randomStep;
        }

        for (int i = 0; i < numberOfUniqueMonsters; i++){
            Set<Integer> intersection = new HashSet<Integer>(allowedCRs);
            intersection.retainAll(getFactors(uniqueMonstersTotalCR[i]));
            do {
                uniqueMonstersCR[i] = Collections.max(intersection);
                intersection.remove(Collections.max(intersection));
            } while (uniqueMonstersCR[i] > CRCap);
        }
    }

    public String Print() {
        return Arrays.toString(uniqueMonstersTotalCR) + Arrays.toString(uniqueMonstersCR);
    }

    public void Damage(Integer monsterIndex) {

    }

    static Set<Integer> getFactors(int n) {
        Set<Integer> factors = new HashSet<>();
        int step = n % 2 == 0 ? 1 : 2;
        for (int i = 1; i <= Math.sqrt(n); i += step) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }
        return factors;
    }
}
