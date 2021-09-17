package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.network.Client;

public interface IPacket {
    default void encode(ByteBuf buf) { throw new UnsupportedOperationException(); }
    default void decode(ByteBuf buf) { throw new UnsupportedOperationException(); }
    default void execute() { throw new UnsupportedOperationException(); }
    default void sendToServer() { Client.send(this); }
}
