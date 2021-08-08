package ru.projectx.clicker.client.managers;

import ru.projectx.clicker.client.Player;
import ru.projectx.clicker.client.utils.InfinityList;

public class EnemyManager {
    private static final InfinityList<Enemy.TYPE> enemies = new InfinityList<>(Enemy.TYPE.values());
    private static Enemy current = EnemyManager.enemies.getNext().create();

    public static Enemy getEnemy() { return EnemyManager.current; }

    public static void load(int i, int hp) {
        EnemyManager.current = EnemyManager.enemies.get(i).create();
        EnemyManager.current.setHp(hp);
        //todo need it
        //GuiManager.setHp((double) EnemyManager.current.getHp() / EnemyManager.current.getMaxHp());
    }

    public static void onHit(int damage) {
        EnemyManager.current.hit(damage);
        if(EnemyManager.current.isDead()) {
            Player.onKill(EnemyManager.current.getType());
            GuiManager.setHp(1);
            EnemyManager.current = EnemyManager.enemies.getNext().create();
            GuiManager.nextEnemy();
        }
        GuiManager.setHp((double) EnemyManager.current.getHp() / EnemyManager.current.getMaxHp());
        GuiManager.updateStats();
    }

    public static class Enemy {
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

        public Enemy.TYPE getType() { return this.type; }

        public boolean isDead() { return this.dead; }

        public int getHp() { return this.hp; }

        public int getMaxHp() { return this.type.getHp(); }

        public int getReward() { return this.type.getReward(); }

        public int getIndex() { return this.type.getIndex(); }

        public void setHp(int hp) { this.hp = hp; }

        public enum TYPE {
            BAD_BOY(100, 5, 0),
            EYE(150, 10, 1),
            ANIME1(200, 101, 2),
            ANIME2(50, 103, 3),
            PUTIN(1000, 10111, 4),
            ANIME3(4534, 10433, 5),
            ANIME4(43532, 50413, 6),
            ANIME5(100000, 100000, 7),
            ANIME6(250000, 170000, 8),
            ANIME10(270000, 170000, 9),
            ANIME11(300000, 190000, 10),
            ANIME12(350000, 250000, 11);


            private final int hp;
            private final int index;
            private final int reward;

            TYPE(int hp, int reward, int index) {
                this.hp = hp;
                this.reward = reward;
                this.index = index;
            }

            public int getHp() { return this.hp; }

            public int getReward() { return this.reward; }

            public Enemy create() { return new Enemy(this); }

            public int getIndex() { return this.index; }
        }
    }
}
