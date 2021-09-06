package ru.projectx.clicker.network;

import ru.projectx.clicker.Config;
import ru.projectx.clicker.Player;
import ru.projectx.clicker.managers.EnemyManager;
import ru.projectx.clicker.managers.GuiManager;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;

    @Override
    public void run() {
        try {
            try {
                socket = new Socket(Config.host, Config.port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (!socket.isClosed() && socket.isConnected() && !this.isInterrupted()) this.read();
            } finally {
                if(socket != null) socket.close();
                if(in != null) in.close();
                if(out != null) out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(String ... params) {
        if(socket.isOutputShutdown()) return;
        try {
            out.flush();
            StringBuilder builder = new StringBuilder();
            for (String param : params) {
                builder.append(param).append("\n");
            }
            out.write(builder.toString());
            out.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void read() throws IOException {
        if(socket.isInputShutdown()) return;
        String i = in.readLine();
        switch (i) {
            case "auth":
                GuiManager.tryAuth(in.readLine());
                break;
            case "syncStats":
                Player.setDamage(Integer.parseInt(in.readLine()));
                Player.setKills(Integer.parseInt(in.readLine()));
                Player.setLevel(Integer.parseInt(in.readLine()));
                Player.setMoney(Integer.parseInt(in.readLine()));
                GuiManager.updateStats();
                break;
            case "syncEnemy":
                EnemyManager.loadOrUpdateEnemy(Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
                break;
        }
    }

    public void quit() {
        try {
            send("quit");
            socket.close();
            this.interrupt();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
