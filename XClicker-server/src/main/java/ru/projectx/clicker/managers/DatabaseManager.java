package ru.projectx.clicker.managers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.projectx.clicker.Config;
import ru.projectx.clicker.data.DBObject;
import ru.projectx.clicker.data.DBType;
import ru.projectx.clicker.utils.LogUtils;
import ru.projectx.clicker.utils.ThreadedIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class DatabaseManager {
    private static HikariDataSource source;

    public static void init() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(Config.database.url);
            DatabaseManager.source = new HikariDataSource(config);
        } catch(Exception e) { e.printStackTrace(); }
    }

    public static Connection connection() throws Exception {
        return DatabaseManager.source.getConnection();
    }

    /**
     * Получает значение по ключу из указанной таблицы
     * @param table название таблицы
     * @param key объект ключа
     * @param value объект значения
     * @return полученное значение или пустой результат
     */
    public static <T> Optional<T> getValue(String table, DBObject key, DBType<T> value) {
        try {
            return ThreadedIO.submit(() -> {
                Connection connection = DatabaseManager.connection();
                String sql = "SELECT " + value.getColumn() + " FROM " + table + " WHERE " + key.getColumn() + " = ?";
                PreparedStatement s = connection.prepareStatement(sql);
                s.setObject(1, key.getValue(), key.getType());
                ResultSet set = s.executeQuery();
                T o = null;
                if(set.next()) o = set.getObject(value.getColumn(), value.getType());
                connection.close();
                s.close();
                set.close();
                LogUtils.info("[DATABASE] Тип запроса: получение значения. Запрос: %s. Ответ %s.", sql, o);
                return o == null ? Optional.empty() : Optional.of(o);
            });
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }
}
