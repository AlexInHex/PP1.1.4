package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1243012430Aa";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Регистрация JDBC драйвера
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединения
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Обработка ошибок
        }
        return connection;
    }
}
