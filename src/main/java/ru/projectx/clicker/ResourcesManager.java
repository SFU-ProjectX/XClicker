package ru.projectx.clicker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourcesManager {

    public static BufferedImage getImage(String name) {
        return ResourcesManager.readImage("/images/" + name + ".png");
    }

    public static BufferedImage getIcon(String name) {
        return ResourcesManager.readImage("/images/icons/" + name + ".png");
    }

    public static BufferedImage readImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(XClicker.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
