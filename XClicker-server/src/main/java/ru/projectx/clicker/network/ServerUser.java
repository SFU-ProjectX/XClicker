package ru.projectx.clicker.network;

import java.io.*;
import java.net.Socket;

class ServerUser extends Thread {
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
            while (!socket.isClosed() && in != null && out != null) {
                String type = in.readLine();
                System.out.println(type);
                if(type != null)
                    switch (type) {
                        case "auth":
                            String user = in.readLine();
                            String password = in.readLine();
                            System.out.println(user);
                            System.out.println(password);
                            this.send("auth", "ok");
                            break;
                }
            }
            Server.disconnect(this);
        } catch (IOException e) {}
    }

    public void send(String ... params) {
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
}
