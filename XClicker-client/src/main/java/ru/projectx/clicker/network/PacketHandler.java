package ru.projectx.clicker.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ru.projectx.clicker.network.packets.IPacket;

public class PacketHandler extends SimpleChannelInboundHandler<IPacket> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, IPacket packet) {
        packet.execute();
    }
}
