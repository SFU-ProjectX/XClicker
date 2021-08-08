package ru.projectx.clicker.client.managers;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.projectx.clicker.client.Player;
import ru.projectx.clicker.client.utils.ImageUtils;

public class GuiManager {
    private static Text player_money, player_kills, player_level;
    private static Button shop, settings, level, enemy;
    private static ProgressBar hp;

    public static void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(ResourcesManager.getResource("main.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("XClicker 1.0.0");
            stage.getIcons().add(new Image(ResourcesManager.getResourceAsStream("images/icons/favicon.png")));
            GuiManager.lookup(scene);
            GuiManager.setLogic();
            GuiManager.nextEnemy();
            GuiManager.setHp(1);
            GuiManager.setPlayerKills(Player.getKills());
            GuiManager.setPlayerLevel(Player.getLevel());
            GuiManager.setPlayerMoney(Player.getMoney());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void lookup(Scene scene) {
        shop = (Button) scene.lookup("#shop");
        settings = (Button) scene.lookup("#settings");
        level = (Button) scene.lookup("#level");
        enemy = (Button) scene.lookup("#enemy");
        hp = (ProgressBar) scene.lookup("#hp");
        player_money = (Text) scene.lookup("#player_money");
        player_kills = (Text) scene.lookup("#player_kills");
        player_level = (Text) scene.lookup("#player_level");
    }

    private static void setLogic() {
        enemy.setOnMouseClicked(event -> EnemyManager.onHit(Player.getDamage()));
    }

    public static void setHp(double percent) {
        hp.setProgress(percent);
    }

    public static void setPlayerKills(Object kills) {
        GuiManager.player_kills.setText(kills.toString());
    }

    public static void setPlayerLevel(Object level) {
        GuiManager.player_level.setText(level.toString());
    }

    public static void setPlayerMoney(Object money) {
        GuiManager.player_money.setText(money.toString());
    }

    public static void nextEnemy() {
        //todo centered
        BackgroundImage back= new BackgroundImage(ImageUtils.convertToFxImageJava8(ResourcesManager.enemies.getNext()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        enemy.setBackground(new Background(back));
        enemy.applyCss();
    }
}
