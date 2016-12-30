package com.epam.task11.util;

import com.epam.task11.entity.db.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<User> users = new ArrayList<>();

    public Storage() {
        init();
    }

    private void init() {
        User user = new User();
        user.setUserName("Dimas");
        user.setEmail("dimonumg@gmail.com");
        user.setFirstName("F");
        user.setLastName("L");
        user.setPassword("pass");
        user.setAvatarName("av4o1YRSFx8.jpg");
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setContainer(List<User> users) {
        this.users = users;
    }
}
