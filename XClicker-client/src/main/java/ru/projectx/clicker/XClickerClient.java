package ru.projectx.clicker;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.projectx.clicker.managers.GuiManager;
import ru.projectx.clicker.managers.ResourcesManager;
import ru.projectx.clicker.network.Client;

public class XClickerClient extends Application {
    private final Client client = new Client();

    @Override
    public void start(Stage stage) {
        client.start();
        ResourcesManager.init();
        GuiManager.start(stage);
    }

    @Override
    public void stop() throws Exception {
        client.quit();
        super.stop();
    }
}
