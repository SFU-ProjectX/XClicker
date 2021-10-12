package ru.projectx.clicker;

import ru.projectx.clicker.managers.DatabaseManager;
import ru.projectx.clicker.managers.UpgradesManager;
import ru.projectx.clicker.network.PacketRegistry;
import ru.projectx.clicker.network.Server;

public class XClickerServer {

    public static void main(String[] args) throws Exception {
        UpgradesManager.init();
        DatabaseManager.init();
        PacketRegistry.init();
        Server.start();
    }
}
