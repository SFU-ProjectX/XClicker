package ru.projectx.clicker.client.managers;

import ru.projectx.clicker.client.XClickerStarter;
import ru.projectx.clicker.client.utils.InfinityList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class ResourcesManager {
    public static final InfinityList<BufferedImage> enemies = new InfinityList<>();

    public static void init() {
        enemies.add(getImage("images/enemies/1.png"));
        enemies.add(getImage("images/enemies/2.png"));
        enemies.add(getImage("images/enemies/3.png"));
        enemies.add(getImage("images/enemies/4.png"));
        enemies.add(getImage("images/enemies/5.png"));
    }

    public static URL getResource(String path) {
        return XClickerStarter.class.getClassLoader().getResource(path);
    }

    public static InputStream getResourceAsStream(String path) {
        return XClickerStarter.class.getClassLoader().getResourceAsStream(path);
    }

    public static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(ResourcesManager.getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
