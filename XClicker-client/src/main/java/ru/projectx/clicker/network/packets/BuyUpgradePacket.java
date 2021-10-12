package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;

public class BuyUpgradePacket implements IPacket {
    private int type, id;

    public BuyUpgradePacket() {}

    public BuyUpgradePacket(int type, int id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(type);
        buf.writeInt(id);
    }

    @Override
    public void decode(ByteBuf buf) {}
}
