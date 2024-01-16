package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "12430";

    private static final SessionFactory sessionFactory;

    private Util() {
        throw new IllegalStateException("Util");
    }

    static {
        try {
            // Регистрация JDBC драйвера
            //Class.forName("com.mysql.cj.jdbc.Driver");

            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.url", JDBC_URL)
                    .setProperty("hibernate.connection.username", JDBC_USER)
                    .setProperty("hibernate.connection.password", JDBC_PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();


        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            // Установка соединения
//            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
