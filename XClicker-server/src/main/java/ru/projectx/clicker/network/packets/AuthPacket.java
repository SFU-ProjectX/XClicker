package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.data.Player;
import ru.projectx.clicker.managers.AuthManager;
import ru.projectx.clicker.network.ServerUser;
import ru.projectx.clicker.utils.ByteBufUtils;
import ru.projectx.clicker.utils.LogUtils;

public class AuthPacket implements IPacket {
    private String login, password;
    private boolean ok;

    public AuthPacket() {}

    public AuthPacket(boolean ok) {
        this.ok = ok;
    }

    @Override
    public void execute(ServerUser user) {
        if(!user.getPlayer().isPresent())
            if(AuthManager.tryAuth(user, login, password)) {
                LogUtils.info("Пользователь %s c логином %s успешно авторизовался", user.getChannel(), login);
                user.loggedIn(login);
                user.getPlayer().ifPresent(Player::syncStats);
                user.getPlayer().ifPresent(Player::syncEnemy);
                new AuthPacket(true).sendToClient(user);
            } else new AuthPacket(false).sendToClient(user);
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeBoolean(ok);
    }

    @Override
    public void decode(ByteBuf buf) {
        this.login = ByteBufUtils.readUTF8String(buf);
        this.password = ByteBufUtils.readUTF8String(buf);
    }
}
