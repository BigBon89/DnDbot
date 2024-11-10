package org.example;

public class Monsters {
    Monster[] monsters;
    double[] easyCRs = {0.125, 0.125, 0.25, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8, 8.5};
    double[] normalCRs = {0.125, 0.25, 0.5, 0.75, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8, 8.5, 9};
    double[] hardCRs = {0.25, 0.5, 0.75, 1, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8, 8.5, 9, 9.5, 10};
    double[] CRCaps = {1, 3, 4, 6, 8, 9, 10, 12, 13, 15, 16, 17, 19, 20, 22, 24, 25, 26, 28, 30};
    public void Monsters(EncounterDifficulty difficulty, Integer playersCount, Integer playersLevel, String monsterFilter) {
        monsters = new Monster[1]; //TODO: случайно заполнить

    }

    public void Print() {

    }

    public void Attack(Integer monsterIndex) {

    }
}
