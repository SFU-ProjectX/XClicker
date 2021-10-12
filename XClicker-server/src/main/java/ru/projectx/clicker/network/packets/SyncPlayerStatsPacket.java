package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;

public class SyncPlayerStatsPacket implements IPacket {
    int damage, kills, level, money, auto_damage;

    public SyncPlayerStatsPacket() {}

    public SyncPlayerStatsPacket(int damage, int kills, int level, int money, int auto_damage) {
        this.damage = damage;
        this.kills = kills;
        this.level = level;
        this.money = money;
        this.auto_damage = auto_damage;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(damage);
        buf.writeInt(kills);
        buf.writeInt(level);
        buf.writeInt(money);
        buf.writeInt(auto_damage);
    }
}
