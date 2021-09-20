package ru.projectx.clicker.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import ru.projectx.clicker.network.packets.IPacket;

import java.util.List;

public class PacketCodec extends ByteToMessageCodec<IPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IPacket msg, ByteBuf out) {
        out.writeInt(PacketRegistry.getId(msg));
        msg.encode(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        IPacket packet = PacketRegistry.getPacket(msg.readInt());
        if(packet != null) {
            packet.decode(msg);
            out.add(packet);
        }
    }
}
