package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.network.ServerUser;

public interface IPacket {
    default void encode(ByteBuf buf) { throw new UnsupportedOperationException(); }
    default void decode(ByteBuf buf) { throw new UnsupportedOperationException(); }
    default void execute(ServerUser user) { throw new UnsupportedOperationException(); }
    default void sendToClient(ServerUser user) { user.getChannel().writeAndFlush(this); }
}
