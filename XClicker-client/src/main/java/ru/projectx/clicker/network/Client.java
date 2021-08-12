package ru.projectx.clicker.network;

import ru.projectx.clicker.Config;
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
                while (!socket.isClosed()) read();
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
        String i = in.readLine();
        System.out.println(i);
        switch (i) {
            case "auth":
                String k = in.readLine();
                System.out.println(k);
                GuiManager.tryAuth(k);
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
