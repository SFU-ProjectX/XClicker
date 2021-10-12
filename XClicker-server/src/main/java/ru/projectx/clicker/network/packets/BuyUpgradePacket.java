package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.managers.UpgradesManager;
import ru.projectx.clicker.network.ServerUser;

public class BuyUpgradePacket implements IPacket {
    private int type, id;

    public BuyUpgradePacket() {}

    @Override
    public void execute(ServerUser user) {
        user.getPlayer().ifPresent(player -> UpgradesManager.onBuy(player, type, id));
    }

    @Override
    public void decode(ByteBuf buf) {
        this.type = buf.readInt();
        this.id = buf.readInt();
    }
}
