package ru.projectx.clicker.data;

import java.sql.JDBCType;

public class DBObject extends DBValue {
    protected final String column;

    protected DBObject(Object value, JDBCType type, String column) {
        super(value, type);
        this.column = column;
    }

    public static DBObject of(Object value, JDBCType type, String column) { return new DBObject(value, type, column); }

    public static DBObject of(Object value, String column) { return new DBObject(value, JDBCType.VARCHAR, column); }

    public String getColumn() { return this.column; }
}
