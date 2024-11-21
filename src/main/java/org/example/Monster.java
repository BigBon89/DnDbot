package org.example;

public class Monster {
    public String type;
    public String name;
    public int challangeRating;
    public Integer health;
    public Integer maxHealth;
    public String page;
    public Boolean isAlive;
    //String type
    public Monster(String page,
                        String name,
                        int challangeRating,
                        String type,
                        Integer maxHealth,
                        Integer health) {
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