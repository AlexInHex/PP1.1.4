package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Util.getConnection();

        UserService userService = new UserServiceImpl();

        try {
            userService.createUsersTable();

            User[] users = {
                    new User("Иван", "Иванов", (byte) 25),
                    new User("Мария", "Петрова", (byte) 30),
                    new User("Алексей", "Сидоров", (byte) 35),
                    new User("Екатерина", "Карпук", (byte) 28)
            };

            for (User user : users) {
                userService.saveUser(user.getName(), user.getLastName(), user.getAge());
                System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
            }

            List<User> userList = userService.getAllUsers();
            for (User user : userList) {
                System.out.println(user);
            }

            userService.cleanUsersTable();
            userService.dropUsersTable();
        } finally {
            Util.closeSessionFactory();
        }
    }
}
