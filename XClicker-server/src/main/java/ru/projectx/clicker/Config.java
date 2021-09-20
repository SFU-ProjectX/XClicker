package ru.projectx.clicker;

public class Config {
    public static final int port = 42632;

    public static class auth {
        public static int std_cooldown = 3000;
        public static int bad_login_or_password_cooldown = 3000;
        public static int bad_login_or_password_multiplier = 2;
    }

    public static class database {
        public static String url = "jdbc:mysql://87.249.44.135:3306/default_db?user=gen_user&password=2lgeaxxn8&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    }
}
