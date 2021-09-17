package ru.projectx.clicker.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ru.projectx.clicker.network.packets.IPacket;
import ru.projectx.clicker.utils.LogUtils;

public class PacketHandler extends SimpleChannelInboundHandler<IPacket> {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        Server.join(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Server.quit(ctx.channel());
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, IPacket packet) throws Exception {
        LogUtils.info("Server received packet %s from %s", packet, ctx);
        Server.get(ctx.channel()).ifPresent(user -> user.handle(packet));
    }
}


