package ru.projectx.clicker.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import ru.projectx.clicker.Config;
import ru.projectx.clicker.network.packets.IPacket;

public class Client extends Thread{
    private static Channel channel;

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

            channel = future.channel();

            // Wait until the connection is closed.
            //future.channel().closeFuture().sync();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    public static void send(IPacket packet) {
        try {
            channel.writeAndFlush(packet);
            //channel.flush();
        } catch(Exception e) { e.printStackTrace(); }
    }
}
