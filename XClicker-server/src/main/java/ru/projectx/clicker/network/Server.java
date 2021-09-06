package ru.projectx.clicker.network;

import ru.projectx.clicker.Config;
import ru.projectx.clicker.utils.LogUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static final LinkedList<ServerUser> clients = new LinkedList<>();

    public static void start() throws IOException {
        LogUtils.info("Запуск сервера");
        ServerSocket server = new ServerSocket(Config.port);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    LogUtils.info("Запрос на подключение от клиента %s", socket.getInetAddress());
                    System.out.println("Запрос от клиента " + socket.getInetAddress());
                    clients.add(new ServerUser(socket));
                    LogUtils.info("Клиент %s успешно подключен", socket.getInetAddress());
                } catch (IOException e) {
                    LogUtils.info("Клиент %s не подключен из-за ошибки", socket.getInetAddress());
                    e.printStackTrace();
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static void disconnect(ServerUser user) {
        Server.clients.remove(user);
        LogUtils.info("Клиент %s отключен", user.getSocket().getInetAddress());
    }
}
