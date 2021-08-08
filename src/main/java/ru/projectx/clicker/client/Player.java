package ru.projectx.clicker.client;

public class Player {
    private static int damage = 10;
    private static int money = 0;
    private static int kills = 0;
    private static int level = 1;

    public static int getDamage() {
        return damage;
    }

    public static int getKills() {
        return kills;
    }

    public static int getLevel() {
        return level;
    }

    public static int getMoney() {
        return money;
    }

    public static void setKills(int kills) {
        Player.kills = kills;
    }

    public static void setLevel(int level) {
        Player.level = level;
    }

    public static void setMoney(int money) {
        Player.money = money;
    }

    public static void addKills() {
        Player.kills++;
    }

    public static void addLevel() {
        Player.level ++;
    }

    public static void addMoney(int money) {
        Player.money += money;
    }
}

