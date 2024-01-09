package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Иван", "Иванов", (byte) 25);
        userDao.saveUser("Мария", "Петрова", (byte) 30);
        userDao.saveUser("Алексей", "Сидоров", (byte) 35);
        userDao.saveUser("Екатерина", "Кузнецова", (byte) 28);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
