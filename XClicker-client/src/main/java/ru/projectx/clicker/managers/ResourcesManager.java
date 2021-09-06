package ru.projectx.clicker.managers;

import ru.projectx.clicker.XClickerClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResourcesManager {
    public static final List<BufferedImage> enemies = new ArrayList<>();

    public static void init() {
        BufferedImage img;
        int i = 1;
        while ((img = ResourcesManager.getImage("images/enemies/" + i + ".png")) != null) {
            ResourcesManager.enemies.add(img);
            i++;
        }
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
