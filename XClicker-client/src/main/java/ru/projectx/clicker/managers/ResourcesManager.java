package ru.projectx.clicker.managers;

import ru.projectx.clicker.XClickerClient;
import ru.projectx.clicker.utils.InfinityList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

public class ResourcesManager {
    public static final InfinityList<BufferedImage> enemies = new InfinityList<>();

    public static void init() {
        BufferedImage img;
        int i = 1;
        while ((img = ResourcesManager.getImage("images/enemies/" + i + ".png")) != null) {
            ResourcesManager.enemies.add(img);
            i++;
        }
        ResourcesManager.enemies.setIndex(EnemyManager.getEnemy().getIndex() - 1);
    }

    public static URL getResource(String path) {
        return XClickerClient.class.getClassLoader().getResource(path);
    }

    public static InputStream getResourceAsStream(String path) {
        return XClickerClient.class.getClassLoader().getResourceAsStream(path);
    }

    public static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(ResourcesManager.getResource(path));
        } catch (Exception e) {
            return null;
        }
    }
}
