package org.example;

public class Monster {
    private String type;
    private String name;
    private double challangeRating;
    private Integer health;
    private Integer maxHealth;

    public void Monster(String type,
                        String name,
                        double challangeRating,
                        Integer health,
                        Integer maxHealth) {
        this.type = type;
        this.name = name;
        this.challangeRating = challangeRating;
        this.health = health;
        this.maxHealth = maxHealth;
    }
}