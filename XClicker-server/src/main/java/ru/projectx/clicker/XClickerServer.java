package ru.projectx.clicker;

import ru.projectx.clicker.network.Server;

public class XClickerServer {

    //todo тут какая-то нереальная нагрузка, видимо при выходе юзера он не удаляется, надо проверить
    public static void main(String[] args) throws Exception {
        Server.start();
    }
}
