package ru.projectx.clicker.client.managers;

import ru.projectx.clicker.client.Player;
import ru.projectx.clicker.client.utils.InfinityList;

public class EnemyManager {
    private static final InfinityList<Enemy.TYPE> enemies = new InfinityList<>(Enemy.TYPE.values());
    private static Enemy current = enemies.getNext().create();

    public static int getEnemyIndex() { return current.getIndex(); }

    public static void load(int i) {
        current = enemies.get(i).create();
    }

    public static void onHit(int damage) {
        current.hit(damage);
        if(current.isDead()) {
            Player.addMoney(current.getReward());
            Player.addKills();
            Player.addLevel();
            GuiManager.setHp(1);
            current = enemies.getNext().create();
            GuiManager.nextEnemy();
        }
        GuiManager.setHp((double) current.getHp() / current.getMaxHp());
        GuiManager.setPlayerKills(Player.getKills());
        GuiManager.setPlayerLevel(Player.getLevel());
        GuiManager.setPlayerMoney(Player.getMoney());
    }

    private static class Enemy {
        private final Enemy.TYPE type;
        private boolean dead;
        private int hp;

        Enemy(TYPE type) {
            this.type = type;
            this.hp = type.getHp();
            this.dead = false;
        }

        public void hit(int damage) {
            if(this.hp <= damage) {
                this.dead = true;
                return;
            }

            this.hp -= damage;
        }

        public Enemy.TYPE getType() { return type; }

        public boolean isDead() { return dead; }

        public int getHp() { return hp; }

        public int getMaxHp() { return type.getHp(); }

        public int getReward() { return type.getReward(); }

        public int getIndex() { return type.getIndex(); }

        private enum TYPE {
            BAD_BOY(100, 5, 0),
            EYE(150, 10, 1),
            ANIME1(200, 101, 2),
            ANIME2(50, 103, 3),
            PUTIN(1000, 10111, 4);

            private final int hp;
            private final int index;
            private final int reward;

            TYPE(int hp, int reward, int index) {
                this.hp = hp;
                this.reward = reward;
                this.index = index;
            }

            public int getHp() { return hp; }

            public int getReward() { return reward; }

            public Enemy create() { return new Enemy(this); }

            public int getIndex() { return index; }
        }
    }
}
