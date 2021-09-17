package ru.projectx.clicker.network.packets;

import ru.projectx.clicker.managers.SaveManager;
import ru.projectx.clicker.network.Server;
import ru.projectx.clicker.network.ServerUser;

public class QuitPacket implements IPacket {
    public QuitPacket() {}

    @Override
    public void execute(ServerUser user) {
        user.getPlayer().ifPresent(SaveManager::save);
        Server.quit(user.getChannel());
    }
}
