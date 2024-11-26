package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Monsters {
    Monster[] monsters;
    private int monstersCount;

    protected Random random;

    public Monsters() {
        monstersCount = 0;
        random = new Random();
    }

    public Monsters(int seed) {
        monstersCount = 0;
        random = new Random(seed);
    }

    Integer[] allowedCRs = {
        1, 2, 4, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112,
        120, 128, 136, 144, 152, 160, 168, 184
    };
    int[] easyCRs = {1, 1, 2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68};
    int[] normalCRs = {1, 2, 4, 6, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72};
    int[] hardCRs = {2, 4, 6, 8, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80};
    int[] CRCaps = {8, 24, 32, 48, 64, 72, 80, 96, 104, 120, 128, 136, 152, 160, 176, 192, 200, 208, 224, 240};

    public void generate(EncounterDifficulty difficulty,
        Integer playersCount,
        Integer playersLevel,
        String monsterFilter)
    {
        int[] uniqueMonstersTotalCR;
        int[] uniqueMonstersCR;


        Set<String>[] monstersByCR = (Set<String>[]) new Set<?>[25];
        Set<Integer> filterCRs = new HashSet<Integer>();
        try {
            File monsterBookFile = new File("c:\\MonsterBook.txt");
            Scanner myReader = new Scanner(monsterBookFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                double CR = Double.parseDouble(data.split("\t")[2]);
                int index = Arrays.binarySearch(allowedCRs, (int) (CR * 8));

                if (Objects.equals(data.split("\t")[3], monsterFilter)) {
                    filterCRs.add((int) (CR * 8));
                } else if (!monsterFilter.isEmpty()) {
                    continue;
                }

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

        int randomStep;
        if (playersLevel < 3) {
            randomStep = 1;
        } else if (playersLevel < 4) {
            randomStep = 2;
        } else if (playersLevel < 5) {
            randomStep = 4;
        } else {
            randomStep = 8;
        }

        if (!monsterFilter.isEmpty()) {
            randomStep = Math.max(randomStep, Collections.min(filterCRs));
        }
        CRBudget -= CRBudget % randomStep;

        int upperBound = Math.min(4, CRBudget / randomStep);
        int numberOfUniqueMonsters = Math.min(random.nextInt(upperBound) + 1, CRBudget);
        uniqueMonstersTotalCR = new int[numberOfUniqueMonsters];
        uniqueMonstersCR = new int[numberOfUniqueMonsters];

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
            if (!monsterFilter.isEmpty()) {
                intersection.retainAll(filterCRs);
            }
            do {
                uniqueMonstersCR[i] = Collections.max(intersection);
                intersection.remove(Collections.max(intersection));
            } while (uniqueMonstersCR[i] > CRCap);
        }

        for (int i = 0; i < numberOfUniqueMonsters; i++) {
            monstersCount += uniqueMonstersTotalCR[i] / uniqueMonstersCR[i];
        }
        monsters = new Monster[monstersCount];

        int[] monsterCRs = new int[monstersCount];
        int[] randomMonsterIndexes = new int[monstersCount];
        int index = 0;
        for (int i = 0; i < uniqueMonstersCR.length; i++) {
            int CRindex = Arrays.binarySearch(allowedCRs, uniqueMonstersCR[i]);
            int randomCRMonsterIndex = random.nextInt(monstersByCR[CRindex].size());
            for (int j = 0; j < uniqueMonstersTotalCR[i] / uniqueMonstersCR[i]; j++) {
                monsterCRs[index] = uniqueMonstersCR[i];
                randomMonsterIndexes[index] = randomCRMonsterIndex;
                index++;
            }
        }

        for (int i = 0; i < monsterCRs.length; i++) {
            int CRindex = Arrays.binarySearch(allowedCRs, monsterCRs[i]);
            int count = 0;
            String monsterLine = "";
            for (String iterMonsterLine : monstersByCR[CRindex]) {
                if (count == randomMonsterIndexes[i]) {
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
            monsterLines[i] = i + ". " + monsters[i].name + " ";
            if (monsters[i].isAlive)
                monsterLines[i] += monsters[i].health + "/" + monsters[i].maxHealth;
            else
                monsterLines[i] += "(DEAD)";
        }
        return String.join("\n", monsterLines);
    }

    public int getMonstersCount() {
        return monstersCount;
    }

    public Monster getMonsterByIndex(int monsterIndex) {
        return monsters[monsterIndex];
    }

    public void damage(Integer monsterIndex, Integer damageAmount) {
        if (!monsters[monsterIndex].isAlive)
            return;

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
