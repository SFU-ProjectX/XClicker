package ru.projectx.clicker.managers;

import io.netty.channel.Channel;
import ru.projectx.clicker.Config;
import ru.projectx.clicker.data.DBObject;
import ru.projectx.clicker.data.DBType;
import ru.projectx.clicker.network.ServerUser;
import ru.projectx.clicker.utils.LogUtils;

import java.util.HashMap;
import java.util.Optional;

public class AuthManager {
    private static final HashMap<Channel, Long> cooldowns = new HashMap<>();

    public static boolean tryAuth(ServerUser user, String login, String password) {
        LogUtils.info("Try auth user %s, with login %s and password %s", user.getChannel(), login, password);

        if(user.getPlayer().isPresent()) {
            LogUtils.info("User %s already logged in", user.getChannel());
            return false;
        }

        if(login == null || password == null || login.isEmpty() || password.isEmpty()) {
            LogUtils.info("User %s try auth with empty or null login or password", user.getChannel());
            return false;
        }

        if(cooldowns.containsKey(user.getChannel())) {
            if(cooldowns.get(user.getChannel()) > System.currentTimeMillis()) {
                LogUtils.info("Cooldown for user %s, do not try auth", user.getChannel());
                return false;
            }
        }

        cooldowns.put(user.getChannel(), System.currentTimeMillis() + Config.auth.std_cooldown);

        try {
            DBObject object = DBObject.of(login, "name");
            DBType<String> value = DBType.of("password");
            Optional<String> db_password = DatabaseManager.getValue("users", object, value);
            if(db_password.isPresent()) {
                if(db_password.get().equals(password)) {
                    LogUtils.info("User %s successfully ended auth with login %s", user.getChannel(), login);
                    clearCooldown(user.getChannel());
                    return true;
                } else {
                    LogUtils.info("User %s cant logged in, incorrect password", user.getChannel());
                    return false;
                }
            } else {
                LogUtils.info("User %s cant logged in, no user with login %s", user.getChannel(), login);
                return false;
            }
        } catch(Exception e) { e.printStackTrace(); }
        return false;
    }

    private static void clearCooldown(Channel channel) {
        cooldowns.remove(channel);
    }
}
