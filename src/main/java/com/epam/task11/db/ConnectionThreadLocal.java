package com.epam.task11.db;

import java.sql.Connection;

public class ConnectionThreadLocal {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal();

    public static Connection getConnection() {
        return threadLocal.get();
    }

    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }
}
