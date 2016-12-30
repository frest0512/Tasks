package com.epam.task11.entity.db;

import com.epam.task11.annotations.DBField;
import com.epam.task11.util.Constants;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @DBField(DBFieldName = Constants.DB_USER_ID)
    private int id;
    @DBField(DBFieldName = Constants.DB_FIRST_NAME)
    private String firstName;
    @DBField(DBFieldName = Constants.DB_LAST_NAME)
    private String lastName;
    @DBField(DBFieldName = Constants.DB_USER_NAME)
    private String userName;
    @DBField(DBFieldName = Constants.DB_EMAIL)
    private String email;
    @DBField(DBFieldName = Constants.DB_PASSWORD)
    private String password;
    @DBField(DBFieldName = Constants.DB_SUBSCRIBE_NAME)
    private boolean isSubscribe;
    @DBField(DBFieldName = Constants.DB_AVATAR_NAME)
    private String avatarName;
    @DBField(DBFieldName = Constants.DB_DATE_BAN)
    private Date dateBan;
    @DBField(DBFieldName = Constants.DB_FAIL_ATTEMPTS)
    private int failAttempts;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getDateBan() {
        return dateBan;
    }

    public int getFailAttempts() {
        return failAttempts;
    }

    public void setFailAttempts(int failAttempts) {
        this.failAttempts = failAttempts;
    }

    public void setDateBan(Date dateBan) {
        this.dateBan = dateBan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public void setSubscribe(boolean subscribe) {
        isSubscribe = subscribe;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isSubscribe=" + isSubscribe +
                ", avatarName='" + avatarName + '\'' +
                ", dateBan=" + dateBan +
                ", failAttempts=" + failAttempts +
                ", role=" + role +
                '}';
    }
}
