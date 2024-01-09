package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        User[] users = {
                new User("Иван", "Иванов", (byte) 25),
                new User("Мария", "Петрова", (byte) 30),
                new User("Алексей", "Сидоров", (byte) 35),
                new User("Екатерина", "Карпук", (byte) 28)
        };

        for (User user : users) {
            userDao.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        }

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
