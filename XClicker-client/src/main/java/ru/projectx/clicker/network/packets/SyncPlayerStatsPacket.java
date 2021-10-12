package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.Player;
import ru.projectx.clicker.managers.GuiManager;

public class SyncPlayerStatsPacket implements IPacket {
    int damage, kills, level, money, auto_damage;

    public SyncPlayerStatsPacket() {}

    @Override
    public void execute() {
        Player.setDamage(damage);
        Player.setKills(kills);
        Player.setLevel(level);
        Player.setMoney(money);
        Player.setAutoDamage(auto_damage);
        GuiManager.updateStats();
    }

    @Override
    public void decode(ByteBuf buf) {
        this.damage = buf.readInt();
        this.kills = buf.readInt();
        this.level = buf.readInt();
        this.money = buf.readInt();
        this.auto_damage = buf.readInt();
    }
}
