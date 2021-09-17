package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;

public class SyncEnemyPacket implements IPacket {
    int index, hp, max_hp;

    public SyncEnemyPacket() {}

    public SyncEnemyPacket(int index, int hp, int max_hp) {
        this.index = index;
        this.hp = hp;
        this.max_hp = max_hp;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(index);
        buf.writeInt(hp);
        buf.writeInt(max_hp);
    }
}
