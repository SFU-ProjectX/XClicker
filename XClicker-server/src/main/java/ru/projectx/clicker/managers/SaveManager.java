package ru.projectx.clicker.managers;

import ru.projectx.clicker.data.Player;
import ru.projectx.clicker.utils.LogUtils;

import java.io.*;

public class SaveManager {
    private static final String name = "XClickerData";
    private static final String appdata = System.getenv("APPDATA");

    public static void save(Player player) {
        try {
            LogUtils.info("Save stats for user %s...", player.getName());
            File save = new File(SaveManager.appdata + File.separator + name + File.separator + player.getName() + ".txt");
            if (!save.exists()) save.createNewFile();
            FileWriter writer = new FileWriter(save);
            writer.write(player.getDamage() + "\n");
            writer.write(player.getAutoDamage() + "\n");
            writer.write(player.getKills() + "\n");
            writer.write(player.getLevel() + "\n");
            writer.write(player.getMoney() + "\n");
            writer.write(player.getEnemies().getEnemy().getIndex() + "\n");
            writer.write(player.getEnemies().getEnemy().getHp() + "\n");
            writer.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void load(Player player) {
        try {
            LogUtils.info("Load stats for user %s...", player.getName());
            File save = new File(SaveManager.appdata + File.separator + name + File.separator + player.getName() + ".txt");
            if (save.exists()) {
                FileInputStream fstream = new FileInputStream(save);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));
                player.setDamage(Integer.parseInt(reader.readLine()));
                player.setAutoDamage(Integer.parseInt(reader.readLine()));
                player.setKills(Integer.parseInt(reader.readLine()));
                player.setLevel(Integer.parseInt(reader.readLine()));
                player.setMoney(Integer.parseInt(reader.readLine()));
                player.getEnemies().load(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
                fstream.close();
                reader.close();
            } else LogUtils.info("Stats for user %s not found", player.getName());
        } catch (IOException e) { e.printStackTrace(); }
    }
}
