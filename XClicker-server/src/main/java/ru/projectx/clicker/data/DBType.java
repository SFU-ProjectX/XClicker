package ru.projectx.clicker.data;

public class DBType<T> {
    protected final String column;
    protected final Class<T> type;

    protected DBType(String column, Class<T> type) {
        this.column = column;
        this.type = type;
    }

    public static <T> DBType<T> of(String column, Class<T> type) { return new DBType<>(column, type); }

    public static DBType<String> of(String column) { return new DBType<>(column, String.class); }

    public String getColumn() { return this.column; }

    public Class<T> getType() { return this.type; }
}
