package org.example;

public class Monster {
    private String type;
    private String name;
    private double challangeRating;
    private Integer health;
    private Integer maxHealth;
    private String page;
    private Boolean isAlive;

    public void Monster(String type,
                        String name,
                        double challangeRating,
                        Integer health,
                        Integer maxHealth,
                        String page) {
        this.type = type;
        this.name = name;
        this.challangeRating = challangeRating;
        this.health = health;
        this.maxHealth = maxHealth;
        this.page = page;
        this.isAlive = true;
    }

    public void setDamage(Integer damage) {
        health -= damage;
        if (health <= 0)
            isAlive = false;
    }
}