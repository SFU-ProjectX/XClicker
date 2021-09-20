package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import ru.projectx.clicker.network.ServerUser;

public interface IPacket {
    default void encode(ByteBuf buf) {  }
    default void decode(ByteBuf buf) {  }
    default void execute(ServerUser user) {  }
    default void sendToClient(ServerUser user) { user.getChannel().writeAndFlush(this).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE); }
}
