package ru.projectx.clicker.network.packets;

import ru.projectx.clicker.network.ServerUser;

public class ClickEnemyPacket implements IPacket {
    public ClickEnemyPacket() {}

    @Override
    public void execute(ServerUser user) {
        user.getPlayer().ifPresent(player -> player.getEnemies().onHit());
    }
}
