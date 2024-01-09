package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoJDBCImpl implements UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "12430";

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users";
    private static final String SAVE_USER_SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private static final String REMOVE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL_USERS_SQL = "SELECT * FROM users";
    private static final String CLEAN_TABLE_SQL = "DELETE FROM users";


    public UserDaoJDBCImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_TABLE_SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_TABLE_SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_SQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_SQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(id, name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
