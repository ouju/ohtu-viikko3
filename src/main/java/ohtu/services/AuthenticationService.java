package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        User user = userDao.findByName(username);
        //for (User user : userDao.listAll()) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
            //    }
        }
        return false;
    }

    public boolean createUser(String username, String password) {
        if (!invalid(username, password)) {
            userDao.add(new User(username, password));
            return true;
        } else {
            return false;
        }
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if (password.length() < 8 || !password.matches(".*\\d+.*") || username.length() < 3 || userDao.listAll().contains(username)) {
            return true;
        }
        return false;
    }
}
