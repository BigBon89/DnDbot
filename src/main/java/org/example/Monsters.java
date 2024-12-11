package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Monsters {
    private Map<Integer, Monster> monsters;

    private Set<String>[] monsterByChallengeRating;
    private Set<Integer> filterChallengeRatings;

    protected Random random;

    public Monsters() {
        random = new Random();
        loadMonstersFromFile();
    }

    public Monsters(int seed) {
        random = new Random(seed);
        loadMonstersFromFile();
    }

    private void loadMonstersFromFile() {
        monsterByChallengeRating = (Set<String>[]) new Set<?>[25];
        filterChallengeRatings = new HashSet<>();

        try {
            String projectPath = System.getProperty("user.dir");
            File monsterBookFile = new File(projectPath + "\\src\\main\\resources\\MonsterBook.txt");
            Scanner myReader = new Scanner(monsterBookFile);

            while (myReader.hasNextLine()) {
                String monsterLine = myReader.nextLine();

                double monsterLineChallengeRating = Double.parseDouble(monsterLine.split("\t")[2]);
                int index = Arrays.binarySearch(allowedChallengeRatings, (int) (monsterLineChallengeRating * 8));

                if (monsterByChallengeRating[index] == null) {
                    monsterByChallengeRating[index] = new HashSet<>();
                }

                monsterByChallengeRating[index].add(monsterLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static final Integer[] allowedChallengeRatings = {
        1, 2, 4, 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112,
        120, 128, 136, 144, 152, 160, 168, 184
    };
    private static final int[] easyChallengeRatings = {
        1, 1, 2, 4, 8, 12, 16, 20, 24, 28,
        32, 36, 40, 44, 48, 52, 56, 60, 64, 68
    };
    private static final int[] normalChallengeRatings = {
        1, 2, 4, 6, 12, 16, 20, 24, 28, 32,
        36, 40, 44, 48, 52, 56, 60, 64, 68, 72
    };
    private static final int[] hardChallengeRatings = {
        2, 4, 6, 8, 20, 24, 28, 32, 36,
        40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80
    };
    private static final int[] challengeRatingCaps = {
        8, 24, 32, 48, 64, 72, 80, 96, 104, 120, 128,
        136, 152, 160, 176, 192, 200, 208, 224, 240
    };

    public void generate(EncounterDifficulty difficulty,
        Integer playersCount,
        Integer playersLevel,
        String monsterFilter
    ) {
        filterChallengeRatings.clear();
        if (!monsterFilter.isEmpty()) {
            for (Set<String> strings : monsterByChallengeRating) {
                if (strings == null) {
                    continue;
                }
                for (String monsterLine : strings) {
                    if (Objects.equals(monsterLine.split("\t")[3], monsterFilter)) {
                        filterChallengeRatings.add((int) (Double.parseDouble(monsterLine.split("\t")[2]) * 8));
                    }
                }
            }
        }

        int challengeRatingBudget = 0;

        switch (difficulty) {
            case EASY -> challengeRatingBudget = easyChallengeRatings[playersLevel - 1] * playersCount;
            case NORMAL -> challengeRatingBudget = normalChallengeRatings[playersLevel - 1] * playersCount;
            case HARD -> challengeRatingBudget = hardChallengeRatings[playersLevel - 1] * playersCount;
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
            randomStep = Math.max(randomStep, Collections.min(filterChallengeRatings));
        }
        challengeRatingBudget -= challengeRatingBudget % randomStep;

        int upperBound = Math.min(4, challengeRatingBudget / randomStep);
        int numberOfUniqueMonsters = Math.min(random.nextInt(upperBound) + 1, challengeRatingBudget);
        int[] uniqueMonstersTotalChallengeRatings = new int[numberOfUniqueMonsters];

        int minCR = randomStep;
        int maxCR = challengeRatingBudget / Math.max(numberOfUniqueMonsters - 1, 1);
        for (int i = 0; i < numberOfUniqueMonsters - 1; i++) {
            uniqueMonstersTotalChallengeRatings[i] = random.nextInt(
                    Math.min(maxCR, challengeRatingBudget) - minCR + 1
            )
                    + minCR;
            uniqueMonstersTotalChallengeRatings[i] = uniqueMonstersTotalChallengeRatings[i]
                    - uniqueMonstersTotalChallengeRatings[i] % randomStep;
            challengeRatingBudget -= uniqueMonstersTotalChallengeRatings[i];
        }
        uniqueMonstersTotalChallengeRatings[numberOfUniqueMonsters - 1] = challengeRatingBudget;

        if (uniqueMonstersTotalChallengeRatings[numberOfUniqueMonsters - 1] == 0) {
            int previousCR = uniqueMonstersTotalChallengeRatings[numberOfUniqueMonsters - 2];
            uniqueMonstersTotalChallengeRatings[numberOfUniqueMonsters - 1] = previousCR / 2
                    - previousCR % randomStep;
            uniqueMonstersTotalChallengeRatings[numberOfUniqueMonsters - 2] = previousCR / 2
                    + (randomStep - previousCR) % randomStep;
        }

        int challengeRatingCap = challengeRatingCaps[playersLevel - 1];
        int[] uniqueMonstersChallengeRatings = new int[numberOfUniqueMonsters];

        for (int i = 0; i < numberOfUniqueMonsters; i++) {
            Set<Integer> intersection = new HashSet<Integer>();
            Collections.addAll(intersection, allowedChallengeRatings);
            intersection.retainAll(MathUtils.getFactors(uniqueMonstersTotalChallengeRatings[i]));
            if (!monsterFilter.isEmpty()) {
                intersection.retainAll(filterChallengeRatings);
            }
            do {
                uniqueMonstersChallengeRatings[i] = Collections.max(intersection);
                intersection.remove(Collections.max(intersection));
            } while (uniqueMonstersChallengeRatings[i] > challengeRatingCap);
        }

        int monstersCount = 0;
        for (int i = 0; i < numberOfUniqueMonsters; i++) {
            monstersCount += uniqueMonstersTotalChallengeRatings[i] / uniqueMonstersChallengeRatings[i];
        }
        monsters = new HashMap<>();

        int[] monsterChallengeRatings = new int[monstersCount];
        int[] randomMonsterIndexes = new int[monstersCount];
        int index = 0;
        for (int i = 0; i < uniqueMonstersChallengeRatings.length; i++) {
            int crIndex = Arrays.binarySearch(allowedChallengeRatings, uniqueMonstersChallengeRatings[i]);
            int randomCRMonsterIndex = random.nextInt(monsterByChallengeRating[crIndex].size());
            for (int j = 0; j < uniqueMonstersTotalChallengeRatings[i] / uniqueMonstersChallengeRatings[i]; j++) {
                monsterChallengeRatings[index] = uniqueMonstersChallengeRatings[i];
                randomMonsterIndexes[index] = randomCRMonsterIndex;
                index++;
            }
        }

        for (int i = 0; i < monsterChallengeRatings.length; i++) {
            int challengeRatingIndex = Arrays.binarySearch(allowedChallengeRatings, monsterChallengeRatings[i]);
            int count = 0;
            String monsterLine = "";
            for (String iterMonsterLine : monsterByChallengeRating[challengeRatingIndex]) {
                if (count == randomMonsterIndexes[i]) {
                    monsterLine = iterMonsterLine;
                    break;
                }
                count++;
            }
            String[] monsterArguments = monsterLine.split("\t");
            Monster monster = new Monster(monsterArguments[0],
                monsterArguments[1],
                    (int) Double.parseDouble(monsterArguments[2]) * 8,
                monsterArguments[3],
                monsterChallengeRatings[i],
                monsterChallengeRatings[i]
            );

            monsters.put(i, monster);
        }
    }

    public String print() {
        StringBuilder result = new StringBuilder();
        for (var entry : monsters.entrySet()) {
            int index = entry.getKey();
            Monster monster = entry.getValue();
            result.append(index).append(". ").append(monster.print()).append("\n");
        }
        return result.toString();
    }

    public int getMonstersCount() {
        return monsters.size();
    }

    public void damage(Integer monsterIndex, Integer damageAmount) {
        Monster monster = monsters.get(monsterIndex);
        if (!monster.isAlive) {
            return;
        }

        monster.setDamage(damageAmount);

        if (!monster.isAlive) {
            monsters.remove(monsterIndex);
        }
    }

    public boolean isValidIndex(int index) {
        return monsters.containsKey(index);
    }
}
