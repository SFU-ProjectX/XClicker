package ru.projectx.clicker.data;

import ru.projectx.clicker.managers.SaveManager;

public class Player {
    private final String name;
    private int damage = 5;
    private int money = 0;
    private int kills = 0;
    private int level = 1;

    public Player(String name) {
        this.name = name;
        SaveManager.load(this);
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getKills() {
        return this.kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addKills() {
        this.kills++;
    }

    public void addLevel() {
        this.level++;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void addDamage(int damage) {
        this.damage += damage;
    }

    public String getName() {
        return this.name;
    }
}
