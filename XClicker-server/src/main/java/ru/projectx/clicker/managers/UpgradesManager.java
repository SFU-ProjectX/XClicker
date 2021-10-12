package ru.projectx.clicker.managers;

import ru.projectx.clicker.data.Player;
import ru.projectx.clicker.network.Server;

import java.sql.Time;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class UpgradesManager {
    private static final HashMap<Integer, HashMap<Integer, Upgrade>> upgrades = new HashMap<>();

    public static void init() {
        UpgradesManager.upgrades.put(0, new HashMap<>());
        UpgradesManager.upgrades.put(1, new HashMap<>());
        UpgradesManager.upgrades.get(0).put(0, new Upgrade(1000, 100));
        UpgradesManager.upgrades.get(0).put(1, new Upgrade(100000, 10000));
        UpgradesManager.upgrades.get(1).put(0, new Upgrade(100000, 50));
        UpgradesManager.upgrades.get(1).put(1, new Upgrade(10000000, 500));
        UpgradesManager.upgrades.get(1).put(2, new Upgrade(100000000, 5000));
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Server.getUsers().forEach(user -> user.getPlayer().ifPresent(player -> {
                    if(player.getAutoDamage() != 0) {
                        player.getEnemies().onHit(player.getAutoDamage());
                    }
                }));
            }
        }, 0, 1000);
    }

    public static void onBuy(Player player, int type, int id) {
        if(UpgradesManager.upgrades.containsKey(type)) {
            if(UpgradesManager.upgrades.get(type).containsKey(id)) {
                Upgrade upgrade = UpgradesManager.upgrades.get(type).get(id);
                if(player.getMoney() >= upgrade.price) {
                    player.setMoney(player.getMoney() - upgrade.price);
                    switch(type) {
                        case 0:
                            player.addDamage(upgrade.damage);
                            break;
                        case 1:
                            player.addAutoDamage(upgrade.damage);
                            break;
                    }
                    player.syncStats();
                }
            }
        }
    }

    public static class Upgrade {
        int price, damage;

        public Upgrade(int price, int damage) {
            this.price = price;
            this.damage = damage;
        }
    }
}
