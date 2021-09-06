package ru.projectx.clicker.managers;

public class EnemyManager {
    private static Enemy current ;

    public static Enemy getEnemy() {
        return EnemyManager.current;
    }

    public static void loadOrUpdateEnemy(int i, int hp, int max_hp) {
        if(current == null || current.getIndex() != i) {
            EnemyManager.current = new Enemy(i, hp, max_hp);
            GuiManager.setHp(1);
            GuiManager.nextEnemy();
        } else EnemyManager.current.setHp(hp);
        GuiManager.setHp((double) EnemyManager.current.getHp() / EnemyManager.current.getMaxHp());
    }

    public static class Enemy {
        private final int index;
        private final int max_hp;
        private int hp;

        Enemy(int i, int hp, int max_hp) {
            this.index = i;
            this.hp = hp;
            this.max_hp = max_hp;
        }

        public int getHp() {
            return this.hp;
        }

        public int getMaxHp() {
            return this.max_hp;
        }

        public int getIndex() {
            return this.index;
        }

        public void setHp(int hp) { this.hp = hp; }
    }
}
