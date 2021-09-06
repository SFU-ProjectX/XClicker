package ru.projectx.clicker.network;

import ru.projectx.clicker.data.Player;
import ru.projectx.clicker.managers.AuthManager;
import ru.projectx.clicker.managers.SaveManager;
import ru.projectx.clicker.utils.LogUtils;

import java.io.*;
import java.net.Socket;

public class ServerUser extends Thread {
    private Player player;
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;

    public ServerUser(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed() && socket.isConnected() && in != null && out != null) {
                String type = in.readLine();
                if(type != null)
                    switch (type) {
                        case "quit":
                            if(this.player != null) SaveManager.save(player);
                            Server.disconnect(this);
                        case "auth":
                            String login = in.readLine();
                            String password = in.readLine();
                            if(AuthManager.tryAuth(this, login, password)) {
                                LogUtils.info("Пользователь %s c логином %s успешно авторизовался", this.socket.getInetAddress(), login);
                                this.player = new Player(login);
                                this.send("sync", player.getDamage(), player.getKills(), player.getLevel(), player.getMoney());
                                this.send("auth", "ok");
                            } else this.send("auth", "no");
                            break;
                }
            }
        } catch (IOException e) {

        } finally {
            //todo до сюда не доходит
            if(this.player != null) SaveManager.save(player);
            Server.disconnect(this);
        }
    }

    public void send(Object ... params) {
        try {
            out.flush();
            StringBuilder builder = new StringBuilder();
            for (Object param : params) {
                builder.append(param).append("\n");
            }
            out.write(builder.toString());
            out.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Socket getSocket() { return this.socket; }

    public Player getPlayer() { return this.player; }
}
