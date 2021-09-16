package ru.projectx.clicker.managers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {
    public static MediaPlayer hit, click, song;

    public static void init() {
        SoundManager.hit = new MediaPlayer(new Media(ResourcesManager.getResource("sounds/hit.mp3").toString()));
        SoundManager.click = new MediaPlayer(new Media(ResourcesManager.getResource("sounds/button_click.mp3").toString()));
        SoundManager.song = new MediaPlayer(new Media(ResourcesManager.getResource("sounds/main_theme.mp3").toString()));
    }

    public static void playClick() {
        SoundManager.click.play();
        SoundManager.click.seek(Duration.ZERO);
    }
}
