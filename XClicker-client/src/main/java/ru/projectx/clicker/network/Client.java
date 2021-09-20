package ru.projectx.clicker.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import ru.projectx.clicker.Config;
import ru.projectx.clicker.network.packets.IPacket;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client extends Thread{
    private static final Queue<IPacket> sendPacketsQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new PacketCodec());
                    p.addLast(new PacketHandler());
                }
            });

            // Start the client.
            ChannelFuture future = b.connect(Config.host, Config.port).sync();

            Channel channel = future.sync().channel();
            while (channel != null && channel.isOpen()) {
                while (!sendPacketsQueue.isEmpty()) {
                    IPacket msg = sendPacketsQueue.poll();
                    channel.writeAndFlush(msg).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
                    channel.flush();
                }
            }
            // Wait until the connection is closed.
            future.channel().closeFuture().sync();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    public static void send(IPacket packet) {
        sendPacketsQueue.add(packet);
    }
}
