package ru.projectx.clicker.managers;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.projectx.clicker.Player;
import ru.projectx.clicker.network.packets.AuthPacket;
import ru.projectx.clicker.network.packets.BuyUpgradePacket;
import ru.projectx.clicker.network.packets.ClickEnemyPacket;
import ru.projectx.clicker.utils.Hash;
import ru.projectx.clicker.utils.ImageUtils;

import java.util.ArrayList;

public class GuiManager {
    private static final ArrayList<Node> GUI_OBJECTS = new ArrayList<>();
    private static Pane menu, auth, game, shop_menu;
    private static Text player_money, player_kills, player_level, player_damage, player_damage_auto;
    private static Button shop, settings, level, enemy, enter, upgrade1, upgrade2, upgradeA1, upgradeA2, upgradeA3;
    private static ProgressBar hp;
    private static TextField login;
    private static PasswordField password;

    public static void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(ResourcesManager.getResource("main.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            //stage.setResizable(false);
            stage.setTitle("XClicker");
            stage.widthProperty().addListener((observable, oldValue, newValue) -> {
                if(Double.isNaN(oldValue.doubleValue())) return;
                double scale = newValue.doubleValue() / oldValue.doubleValue();
                GUI_OBJECTS.forEach(node -> {
                    if(node instanceof Region) {
                        Region region = (Region) node;
                        region.setLayoutX(region.getLayoutX() * scale);
                        region.setPrefWidth(region.getPrefWidth() * scale);
                    }
                });
            });

            stage.heightProperty().addListener((observable, oldValue, newValue) -> {
                if(Double.isNaN(oldValue.doubleValue())) return;
                double scale = newValue.doubleValue() / oldValue.doubleValue();
                GUI_OBJECTS.forEach(node -> {
                    if(node instanceof Region) {
                        Region region = (Region) node;
                        region.setLayoutY(region.getLayoutY() * scale);
                        region.setPrefHeight(region.getPrefHeight() * scale);
                    }
                });
            });

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
        GuiManager.shop = GuiManager.get(scene, "#shop", Button.class);
        GuiManager.settings = GuiManager.get(scene, "#settings", Button.class);
        GuiManager.level = GuiManager.get(scene, "#level", Button.class);
        GuiManager.enemy = GuiManager.get(scene, "#enemy", Button.class);
        GuiManager.hp = GuiManager.get(scene, "#hp", ProgressBar.class);
        GuiManager.enter = GuiManager.get(scene, "#enter", Button.class);
        GuiManager.player_money = GuiManager.get(scene, "#player_money", Text.class);
        GuiManager.player_kills = GuiManager.get(scene, "#player_kills", Text.class);
        GuiManager.player_level = GuiManager.get(scene, "#player_level", Text.class);
        GuiManager.player_level = GuiManager.get(scene, "#player_level", Text.class);
        GuiManager.player_damage = GuiManager.get(scene, "#player_damage", Text.class);
        GuiManager.player_damage_auto = GuiManager.get(scene, "#player_damage_auto", Text.class);
        GuiManager.login = GuiManager.get(scene, "#login", TextField.class);
        GuiManager.password = GuiManager.get(scene, "#password", PasswordField.class);
        GuiManager.auth = GuiManager.get(scene, "#auth", Pane.class);
        GuiManager.menu = GuiManager.get(scene, "#menu", Pane.class);
        GuiManager.game = GuiManager.get(scene, "#game", Pane.class);
        GuiManager.shop_menu = GuiManager.get(scene, "#shop_menu", Pane.class);
        GuiManager.upgrade1 = GuiManager.get(scene, "#buyButton_w1", Button.class);
        GuiManager.upgrade2 = GuiManager.get(scene, "#buyButton_w2", Button.class);
        GuiManager.upgradeA1 = GuiManager.get(scene, "#buyButton_i1", Button.class);
        GuiManager.upgradeA2 = GuiManager.get(scene, "#buyButton_i2", Button.class);
        GuiManager.upgradeA3 = GuiManager.get(scene, "#buyButton_i3", Button.class);
    }

    private static <N extends Node> N get(Scene scene, String name, Class<N> type) {
        N node = type.cast(scene.lookup(name));
        GUI_OBJECTS.add(node);
        return node;
    }

    private static void setLogic() {
        GuiManager.upgrade1.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            GuiManager.onUpgrade(0, 0);
        });
        GuiManager.upgrade2.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            GuiManager.onUpgrade(0, 1);
        });
        GuiManager.upgradeA1.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            GuiManager.onUpgrade(1, 0);
        });
        GuiManager.upgradeA2.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            GuiManager.onUpgrade(1, 1);
        });
        GuiManager.upgradeA3.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            GuiManager.onUpgrade(1, 2);
        });
        GuiManager.settings.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
        });
        GuiManager.level.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
        });
        GuiManager.enemy.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            new ClickEnemyPacket().sendToServer();
            SoundManager.hit.play();
            SoundManager.hit.seek(Duration.ZERO);
        });
        GuiManager.enter.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            if(!GuiManager.login.getText().isEmpty() && !GuiManager.password.getText().isEmpty()) {
                new AuthPacket(GuiManager.login.getText(), Hash.getHash(Hash.getHash(GuiManager.password.getText()))).sendToServer();
            }
        });
        GuiManager.shop.setOnMouseClicked(event -> {
            if(event.getButton() != MouseButton.PRIMARY) return;
            SoundManager.playClick();
            GuiManager.shop_menu.setVisible(!GuiManager.shop_menu.isVisible());
        });
    }

    public static void onUpgrade(int type, int id) {
        new BuyUpgradePacket(type, id).sendToServer();
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

    public static void setPlayerAutoDamage(Object damage) {
        GuiManager.player_damage_auto.setText(damage.toString());
    }

    public static void nextEnemy() {
        BackgroundImage back = new BackgroundImage(ImageUtils.convertToFxImageJava8(ResourcesManager.enemies.get(EnemyManager.getEnemy().getIndex())), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(-1, -1, true, true, true, false));
        GuiManager.enemy.setBackground(new Background(back));
        GuiManager.enemy.applyCss();
    }

    public static void updateStats() {
        GuiManager.setPlayerKills(Player.getKills());
        GuiManager.setPlayerLevel(Player.getLevel());
        GuiManager.setPlayerMoney(Player.getMoney());
        GuiManager.setPlayerDamage(Player.getDamage());
        //GuiManager.setPlayerAutoDamage(Player.getAutoDamage());
    }

    public static void tryAuth(boolean ok) {
        if(ok) {
            GuiManager.auth.setVisible(false);
            SoundManager.song.setCycleCount(Timeline.INDEFINITE);
            SoundManager.song.play();
        } else {
            //todo fix
            GuiManager.auth.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
    }
}