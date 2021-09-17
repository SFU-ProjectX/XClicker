package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.managers.EnemyManager;

public class SyncEnemyPacket implements IPacket {
    int index, hp, max_hp;

    public SyncEnemyPacket() {}

    @Override
    public void execute() {
        EnemyManager.loadOrUpdateEnemy(index, hp, max_hp);
    }

    @Override
    public void decode(ByteBuf buf) {
        this.index = buf.readInt();
        this.hp = buf.readInt();
        this.max_hp = buf.readInt();
    }
}
