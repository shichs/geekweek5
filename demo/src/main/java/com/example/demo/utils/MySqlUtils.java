package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlUtils {
    private final static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private final static String driverName = "com.mysql.cj.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/my_word";
    private final static String user = "root";
    private final static String pass = "123456";

    static {
        try {
            Class.forName(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (connectionThreadLocal.get() == null || connectionThreadLocal.get().isClosed()) {
                connectionThreadLocal.set(DriverManager.getConnection(url, user, pass));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connectionThreadLocal.get();
    }

    public static ThreadLocal<Connection> getConnectionThreadLocal() {
        return connectionThreadLocal;
    }

    public static void setConnectionThreadLocal(Connection connection) {
        connectionThreadLocal.set(connection);
    }
}
