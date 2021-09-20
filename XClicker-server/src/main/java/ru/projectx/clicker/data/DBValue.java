package ru.projectx.clicker.data;

import java.sql.JDBCType;

public class DBValue {
    protected final Object value;
    protected final JDBCType type;

    protected DBValue(Object value, JDBCType type) {
        this.value = value;
        this.type = type;
    }

    public static DBValue of(Object value, JDBCType type) { return new DBValue(value, type); }

    public static DBValue of(Object value) { return new DBValue(value, JDBCType.VARCHAR); }

    public Object getValue() { return this.value; }

    public JDBCType getType() { return this.type; }
}
