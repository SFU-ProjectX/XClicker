package ru.projectx.clicker;

public class Player {
    private static int auto_damage = 0;
    private static int damage = 10;
    private static int money = 0;
    private static int kills = 0;
    private static int level = 1;

    public static int getAutoDamage() { return Player.auto_damage; }

    public static int getDamage() {
        return Player.damage;
    }

    public static void setDamage(int damage) {
        Player.damage = damage;
    }

    public static int getKills() {
        return kills;
    }

    public static void setKills(int kills) {
        Player.kills = kills;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Player.level = level;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Player.money = money;
    }

    public static void addKills() {
        Player.kills++;
    }

    public static void addLevel() {
        Player.level++;
    }

    public static void addMoney(int money) {
        Player.money += money;
    }

    public static void addDamage(int damage) {
        Player.damage += damage;
    }

    public static void setAutoDamage(int auto_damage) { Player.auto_damage = auto_damage; }
}

