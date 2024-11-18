package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Monsters {
    Monster[] monsters;
    int[] uniqueMonstersTotalCR;
    int[] uniqueMonstersCR;

    protected Random random;

    public Monsters() {
        random = new Random();
    }

    public Monsters(int seed) {
        random = new Random(seed);
    }

    Integer[] allowedCRs = {1, 2, 4, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120, 128, 136, 144, 152, 160, 168, 184};
    int[] easyCRs = {1, 1, 2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68};
    int[] normalCRs = {1, 2, 4, 6, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72};
    int[] hardCRs = {2, 4, 6, 8, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80};
    int[] CRCaps = {8, 24, 32, 48, 64, 72, 80, 96, 104, 120, 128, 136, 152, 160, 176, 192, 200, 208, 224, 240};

    public void generate(EncounterDifficulty difficulty, Integer playersCount, Integer playersLevel, String monsterFilter) {
        Set<String>[] monstersByCR = (Set<String>[]) new Set<?>[25];
        try {
            File monsterBookFile = new File("c:\\MonsterBook.txt");
            Scanner myReader = new Scanner(monsterBookFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                double CR = Double.parseDouble(data.split("\t")[2]);
                int index = Arrays.binarySearch(allowedCRs, (int) (CR * 8));

                if (monstersByCR[index] == null) {
                    monstersByCR[index] = new HashSet<>();
                }

                monstersByCR[index].add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int CRBudget = 0;
        int CRCap = CRCaps[playersLevel - 1];
        switch (difficulty) {
            case EASY -> CRBudget = easyCRs[playersLevel - 1] * playersCount;
            case NORMAL -> CRBudget = normalCRs[playersLevel - 1] * playersCount;
            case HARD -> CRBudget = hardCRs[playersLevel - 1] * playersCount;
        }

        int numberOfUniqueMonsters = Math.min(random.nextInt(4) + 1, CRBudget);
        uniqueMonstersTotalCR = new int[numberOfUniqueMonsters];
        uniqueMonstersCR = new int[numberOfUniqueMonsters];

        int randomStep;
        if (playersLevel < 3) {
            randomStep = 1;
        } else if (playersLevel < 4) {
            randomStep = Math.min(2, CRBudget / 2);
        } else if (playersLevel < 5) {
            randomStep = Math.min(4, CRBudget / 4);
        } else {
            randomStep = Math.min(8, CRBudget / 8);
            CRBudget = CRBudget - CRBudget % 8;
        }

        int minCR = randomStep;
        int maxCR = CRBudget / Math.max(numberOfUniqueMonsters - 1, 1);
        for (int i = 0; i < numberOfUniqueMonsters - 1; i++) {
            uniqueMonstersTotalCR[i] = random.nextInt(Math.min(maxCR, CRBudget) - minCR + 1) + minCR;
            uniqueMonstersTotalCR[i] = uniqueMonstersTotalCR[i] - uniqueMonstersTotalCR[i] % randomStep;
            CRBudget -= uniqueMonstersTotalCR[i];
        }
        uniqueMonstersTotalCR[numberOfUniqueMonsters - 1] = CRBudget;

        if (uniqueMonstersTotalCR[numberOfUniqueMonsters - 1] == 0) {
            int previousCR = uniqueMonstersTotalCR[numberOfUniqueMonsters - 2];
            uniqueMonstersTotalCR[numberOfUniqueMonsters - 1] = previousCR / 2 - previousCR % randomStep;
            uniqueMonstersTotalCR[numberOfUniqueMonsters - 2] = previousCR / 2 + (randomStep - previousCR) % randomStep;
        }

        for (int i = 0; i < numberOfUniqueMonsters; i++) {
            Set<Integer> intersection = new HashSet<Integer>();
            Collections.addAll(intersection, allowedCRs);
            intersection.retainAll(getFactors(uniqueMonstersTotalCR[i]));
            do {
                uniqueMonstersCR[i] = Collections.max(intersection);
                intersection.remove(Collections.max(intersection));
            } while (uniqueMonstersCR[i] > CRCap);
        }

        int numberOfMonsters = 0;
        for (int i = 0; i < numberOfUniqueMonsters; i++) {
            numberOfMonsters += uniqueMonstersTotalCR[i] / uniqueMonstersCR[i];
        }
        monsters = new Monster[numberOfMonsters];

        int[] monsterCRs = new int[numberOfMonsters];
        int index = 0;
        for (int i = 0; i < uniqueMonstersCR.length; i++) {
            for (int j = 0; j < uniqueMonstersTotalCR[i] / uniqueMonstersCR[i]; j++) {
                monsterCRs[index] = uniqueMonstersCR[i];
                index++;
            }
        }

        for (int i = 0; i < monsterCRs.length; i++) {
            int CRindex = Arrays.binarySearch(allowedCRs, monsterCRs[i]);
            int randomCRMonsterIndex = random.nextInt(monstersByCR[CRindex].size());
            int count = 0;
            String monsterLine = "";
            for (String iterMonsterLine : monstersByCR[CRindex]) {
                if (count == randomCRMonsterIndex) {
                    monsterLine = iterMonsterLine;
                    break;
                }
                count++;
            }
            String[] monsterArguments = monsterLine.split("\t");
            monsters[i] = new Monster(monsterArguments[0], monsterArguments[1], (int) Double.parseDouble(monsterArguments[2]) * 8, monsterArguments[3], monsterCRs[i], monsterCRs[i]);
        }
    }

    public String print() {
        String[] monsterLines = new String[monsters.length];
        for (int i = 0; i < monsters.length; i++) {
            monsterLines[i] = monsters[i].name + " " + monsters[i].health + "/" + monsters[i].maxHealth;
        }
        return String.join("\n", monsterLines);
    }

    public void damage(Integer monsterIndex, Integer damageAmount) {
        monsters[monsterIndex].setDamage(damageAmount);
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
