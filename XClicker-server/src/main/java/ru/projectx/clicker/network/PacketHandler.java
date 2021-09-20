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
        LogUtils.info("Some exception with user %s, disconnecting his", ctx.channel());
        Server.quit(ctx.channel());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPacket packet) {
        LogUtils.info("Server received packet %s from %s", packet, ctx);
        Server.get(ctx.channel()).ifPresent(user -> user.handle(packet));
    }
}


