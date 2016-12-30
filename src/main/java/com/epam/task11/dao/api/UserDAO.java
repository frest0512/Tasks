package com.epam.task11.dao.api;

import com.epam.task11.entity.db.User;

import java.util.Date;


public interface UserDAO {
    User get(String login, String password);

    boolean exist(String login);

    boolean add(User user);

    boolean resetNumberOfFailAttempts(User user);

    boolean incrementNumberOfFailAttempts(User user);

    boolean setBanDate(User user, Date time);
}
