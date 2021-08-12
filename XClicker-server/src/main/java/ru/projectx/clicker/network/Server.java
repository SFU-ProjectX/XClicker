package ru.projectx.clicker.network;

import ru.projectx.clicker.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static final LinkedList<ServerUser> clients = new LinkedList<>();

    public static void start() throws IOException {
        System.out.println("Запуск сервера");
        ServerSocket server = new ServerSocket(Config.port);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    System.out.println("Запрос от клиента " + socket.getInetAddress());
                    clients.add(new ServerUser(socket));
                    System.out.println("Клиент успешно добавлен");
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static void disconnect(ServerUser user) {
        Server.clients.remove(user);
        System.out.println("Клиент удален");
    }
}
