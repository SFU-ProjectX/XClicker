package ru.projectx.clicker.client;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.projectx.clicker.client.managers.GuiManager;
import ru.projectx.clicker.client.managers.ResourcesManager;
import ru.projectx.clicker.client.managers.SaveManager;

public class XClickerStarter extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SaveManager.load();
        ResourcesManager.init();
        GuiManager.start(stage);
    }

    @Override
    public void stop() throws Exception {
        SaveManager.save();
        super.stop();
    }
}
