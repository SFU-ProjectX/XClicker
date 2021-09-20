package ru.projectx.clicker.network.packets;

import io.netty.buffer.ByteBuf;
import ru.projectx.clicker.network.Client;

public interface IPacket {
    default void encode(ByteBuf buf) {  }
    default void decode(ByteBuf buf) {  }
    default void execute() {  }
    default void sendToServer() { Client.send(this); }
}
