package ru.projectx.clicker.managers;

import ru.projectx.clicker.network.ServerUser;
import ru.projectx.clicker.utils.LogUtils;

public class AuthManager {

    public static boolean tryAuth(ServerUser user, String login, String password) {
        //todo как-то проверять пользователей
        LogUtils.info("Try auth user %s, with login %s and password %s", user.getChannel(), login, password);
        return login != null && password != null && !login.isEmpty() && !password.isEmpty();
    }
}
