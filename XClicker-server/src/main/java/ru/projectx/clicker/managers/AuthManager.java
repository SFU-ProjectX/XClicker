package ru.projectx.clicker.managers;

import ru.projectx.clicker.network.ServerUser;

public class AuthManager {

    public static boolean tryAuth(ServerUser user, String login, String password) {
        //todo как-то проверять пользователей
        System.out.println("Попытка авторизации! login=" + login + " pass=" + password);
        return login != null && password != null && !login.isEmpty() && !password.isEmpty();
    }
}
