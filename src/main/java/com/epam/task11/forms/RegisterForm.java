package com.epam.task11.forms;

import com.epam.task11.entity.db.User;

public class RegisterForm {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String isSubscribe;
    private String avatarName;
    private String captcha;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public User createUserFromForm() {
        User user = new User();
        user.setPassword(getPassword());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setEmail(getEmail());
        user.setSubscribe("on".equals(getIsSubscribe()));
        user.setUserName(getUserName());
        user.setAvatarName(getAvatarName());
        return user;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isSubscribe='" + isSubscribe + '\'' +
                ", avatarName='" + avatarName + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
