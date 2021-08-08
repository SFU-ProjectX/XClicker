package ru.projectx.clicker.client.managers;

import ru.projectx.clicker.client.Player;

import java.io.*;

public class SaveManager {
    private static final String name = "XClickerData";
    private static final String appdata = System.getenv("APPDATA");

    public static void save() {
        try {
            File save = new File(appdata + "/" + name);
            if(!save.exists()) save.createNewFile();
            FileWriter writer = new FileWriter(save);
            writer.write(Player.getDamage() + "\n");
            writer.write(Player.getKills() + "\n");
            writer.write(Player.getLevel() + "\n");
            writer.write(Player.getMoney() + "\n");
            writer.write(EnemyManager.getEnemyIndex() + "\n");
            writer.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void load() {
        try {
            File save = new File(appdata + "/" + name);
            if(save.exists())  {
                FileInputStream fstream = new FileInputStream(save);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));
                Player.setDamage(Integer.parseInt(reader.readLine()));
                Player.setKills(Integer.parseInt(reader.readLine()));
                Player.setLevel(Integer.parseInt(reader.readLine()));
                Player.setMoney(Integer.parseInt(reader.readLine()));
                EnemyManager.load(Integer.parseInt(reader.readLine()));
                fstream.close();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
