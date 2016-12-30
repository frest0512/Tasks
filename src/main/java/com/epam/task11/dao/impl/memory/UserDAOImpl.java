package com.epam.task11.dao.impl.memory;

import com.epam.task11.dao.api.UserDAO;
import com.epam.task11.entity.db.User;
import com.epam.task11.util.Storage;

import java.util.Date;

public class UserDAOImpl implements UserDAO {

    private Storage storage;

    public UserDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public User get(String login, String password) {
        for (User user : storage.getUsers()) {
            if (user.getUserName().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean exist(String login) {
        for (User user : storage.getUsers()) {
            if (user.getUserName().equals(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(User user) {
        return storage.getUsers().add(user);
    }

    @Override
    public boolean resetNumberOfFailAttempts(User user) {
        return false;
    }

    @Override
    public boolean incrementNumberOfFailAttempts(User user) {
        return false;
    }

    @Override
    public boolean setBanDate(User user, Date time) {
        return false;
    }

}
