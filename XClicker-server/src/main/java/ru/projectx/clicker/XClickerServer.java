package ru.projectx.clicker;

import ru.projectx.clicker.network.PacketRegistry;
import ru.projectx.clicker.network.Server;

public class XClickerServer {

    public static void main(String[] args) throws Exception {
        PacketRegistry.init();
        Server.start();
    }
}
