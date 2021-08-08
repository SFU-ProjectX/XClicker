package ru.projectx.clicker.client;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.projectx.clicker.client.managers.EnemyManager;
import ru.projectx.clicker.client.managers.GuiManager;
import ru.projectx.clicker.client.managers.ResourcesManager;

public class XClickerStarter extends Application {

    @Override
    public void start(Stage stage) {
        ResourcesManager.init();
        GuiManager.start(stage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }
}
