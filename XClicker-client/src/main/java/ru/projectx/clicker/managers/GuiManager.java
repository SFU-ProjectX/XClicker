package ru.projectx.clicker.managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.projectx.clicker.Player;
import ru.projectx.clicker.network.Client;
import ru.projectx.clicker.utils.ImageUtils;

public class GuiManager {
    private static Pane menu, auth, game;
    private static TabPane shop_menu;
    private static Text player_money, player_kills, player_level, player_damage;
    private static Button shop, settings, level, enemy, enter;
    private static ProgressBar hp;
    private static TextField login;
    private static PasswordField password;

    public static void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(ResourcesManager.getResource("main.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("XClicker");
            stage.getIcons().add(new Image(ResourcesManager.getResourceAsStream("images/icons/favicon.png")));
            GuiManager.lookup(scene);
            GuiManager.setLogic();
            GuiManager.updateStats();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void lookup(Scene scene) {
        GuiManager.shop = (Button) scene.lookup("#shop");
        GuiManager.settings = (Button) scene.lookup("#settings");
        GuiManager.level = (Button) scene.lookup("#level");
        GuiManager.enemy = (Button) scene.lookup("#enemy");
        GuiManager.hp = (ProgressBar) scene.lookup("#hp");
        GuiManager.enter = (Button) scene.lookup("#enter");
        GuiManager.player_money = (Text) scene.lookup("#player_money");
        GuiManager.player_kills = (Text) scene.lookup("#player_kills");
        GuiManager.player_level = (Text) scene.lookup("#player_level");
        GuiManager.player_level = (Text) scene.lookup("#player_level");
        GuiManager.player_damage = (Text) scene.lookup("#player_damage");
        GuiManager.login = (TextField) scene.lookup("#login");
        GuiManager.password = (PasswordField) scene.lookup("#password");
        GuiManager.auth = (Pane) scene.lookup("#auth");
        GuiManager.menu = (Pane) scene.lookup("#menu");
        GuiManager.game = (Pane) scene.lookup("#game");
        GuiManager.shop_menu = (TabPane) scene.lookup("#shop_menu");
    }

    private static void setLogic() {
        GuiManager.enemy.setOnMouseClicked(event -> Client.send("click"));
        GuiManager.enter.setOnMouseClicked(event -> {
            if(!GuiManager.login.getText().isEmpty() && !GuiManager.password.getText().isEmpty()) {
                Client.send("auth", GuiManager.login.getText(), GuiManager.password.getText());
            }
        });

        GuiManager.shop.setOnMouseClicked(event -> {
            if (GuiManager.shop_menu.isDisable()) {
                GuiManager.shop_menu.setDisable(false);
                GuiManager.shop_menu.setVisible(true);
            } else {
                GuiManager.shop_menu.setDisable(true);
                GuiManager.shop_menu.setVisible(false);
            }
        });
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

    public static void setPlayerDamage(Object damage) {
        GuiManager.player_damage.setText(damage.toString());
    }

    public static void nextEnemy() {
        //todo centered
        BackgroundImage back = new BackgroundImage(ImageUtils.convertToFxImageJava8(ResourcesManager.enemies.get(EnemyManager.getEnemy().getIndex())), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        GuiManager.enemy.setBackground(new Background(back));
        GuiManager.enemy.applyCss();
    }

    public static void updateStats() {
        GuiManager.setPlayerKills(Player.getKills());
        GuiManager.setPlayerLevel(Player.getLevel());
        GuiManager.setPlayerMoney(Player.getMoney());
        GuiManager.setPlayerDamage(Player.getDamage());
    }

    public static void tryAuth(String answer) {
        if(answer.equals("ok")) {
            GuiManager.auth.setDisable(true);
            GuiManager.auth.setVisible(false);
        } else {
            //todo fix
            GuiManager.auth.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
    }
}
