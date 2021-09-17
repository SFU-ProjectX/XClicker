package ru.projectx.clicker.data;

import ru.projectx.clicker.managers.SaveManager;
import ru.projectx.clicker.network.ServerUser;
import ru.projectx.clicker.network.packets.SyncEnemyPacket;
import ru.projectx.clicker.network.packets.SyncPlayerStatsPacket;

public class Player {
    private final ServerUser user;
    private final Enemies enemies;
    private final String name;
    private int damage = 5;
    private int money = 0;
    private int kills = 0;
    private int level = 1;

    public Player(String name, ServerUser user) {
        this.user = user;
        this.name = name;
        this.enemies = new Enemies(this);
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

    public Enemies getEnemies() { return this.enemies; }

    public ServerUser getUser() { return this.user; }

    public void onKill(Enemies.Enemy type) {
        this.addKills();
        this.addLevel();
        this.addMoney(type.getReward());
        this.syncStats();
    }

    public void syncStats() {
        new SyncPlayerStatsPacket(this.getDamage(), this.getKills(), this.getLevel(), this.getMoney()).sendToClient(this.user);
    }

    public void syncEnemy() {
        new SyncEnemyPacket(this.getEnemies().getEnemy().getIndex(), this.getEnemies().getEnemy().getHp(), this.getEnemies().getEnemy().getMaxHp()).sendToClient(this.user);
    }

    @Override
    public String toString() { return "Player{" + "name='" + name + '}'; }
}
