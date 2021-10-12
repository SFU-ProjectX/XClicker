package ru.projectx.clicker.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import ru.projectx.clicker.Config;
import ru.projectx.clicker.utils.LogUtils;

import java.util.LinkedList;
import java.util.Optional;

public class Server {
    private static final LinkedList<ServerUser> users = new LinkedList<>();

    public static void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new PacketCodec());
                    p.addLast(new PacketHandler());
                    //p.addLast(new LoggingHandler(LogLevel.DEBUG));
                }
            });

            // Start the server.
            ChannelFuture f = b.bind(Config.port).sync();
            LogUtils.info("Starting server");

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void join(Channel channel) {
        LogUtils.info("Client joined - %s", channel);
        users.add(new ServerUser(channel));
    }

    public static void quit(Channel channel) {
        LogUtils.info("Closing connection for client - %s", channel);
        Server.get(channel).ifPresent(users::remove);
    }

    public static Optional<ServerUser> get(Channel channel) { return users.stream().filter(user -> user.getChannel().equals(channel)).findFirst(); }

    public static LinkedList<ServerUser> getUsers() {
        return users;
    }
}
