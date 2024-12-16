package org.example;

public class Monster {
    public String type;
    public String name;
    public int challangeRating;
    public Integer health;
    public Integer maxHealth;
    public String page;
    public Boolean isAlive;

    public Monster(
            String page,
            String name,
            int challangeRating,
            String type,
            Integer maxHealth,
            Integer health
    ) {
        this.page = page;
        this.name = name;
        this.challangeRating = challangeRating;
        this.type = type;
        this.maxHealth = maxHealth;
        this.health = health;
        this.isAlive = true;
    }

    public void setDamage(Integer damage) {
        health -= damage;
        if (health <= 0) {
            isAlive = false;
        }
    }

    public String getName() {
        return name;
    }

    public String print() {
        return name + " " + health + "/" + maxHealth;
    }
}