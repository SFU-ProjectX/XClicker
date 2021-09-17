package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;

public class SyncPlayerStatsPacket implements IPacket {
    int damage, kills, level, money;

    public SyncPlayerStatsPacket() {}

    public SyncPlayerStatsPacket(int damage, int kills, int level, int money) {
        this.damage = damage;
        this.kills = kills;
        this.level = level;
        this.money = money;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(damage);
        buf.writeInt(kills);
        buf.writeInt(level);
        buf.writeInt(money);
    }
}
