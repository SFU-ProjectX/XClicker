package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.managers.GuiManager;
import ru.projectx.clicker.utils.ByteBufUtils;

public class AuthPacket implements IPacket {
    private String login, password;
    private boolean ok;

    public AuthPacket() {}

    public AuthPacket(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void execute() {
        GuiManager.tryAuth(ok);
    }

    @Override
    public void encode(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.login);
        ByteBufUtils.writeUTF8String(buf, this.password);
    }

    @Override
    public void decode(ByteBuf buf) {
        this.ok = buf.readBoolean();
    }
}
