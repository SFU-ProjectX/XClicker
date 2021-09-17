package ru.projectx.clicker;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.projectx.clicker.managers.GuiManager;
import ru.projectx.clicker.managers.ResourcesManager;
import ru.projectx.clicker.managers.SoundManager;
import ru.projectx.clicker.network.Client;
import ru.projectx.clicker.network.PacketRegistry;
import ru.projectx.clicker.network.packets.QuitPacket;

public class XClickerClient extends Application {
    private Client client;

    @Override
    public void start(Stage stage) throws Exception {
        PacketRegistry.init();
        ResourcesManager.init();
        SoundManager.init();
        GuiManager.start(stage);
        client = new Client();
        client.start();
    }

    @Override
    public void stop() throws Exception {
        new QuitPacket().sendToServer();
        client.interrupt();
        super.stop();
    }
}
