package com.epam.task11;

import com.epam.task11.forms.LoginForm;
import com.epam.task11.validators.FormValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class LoginFormValidatorTest {


    private LoginForm loginForm;

    @Before
    public void setUp() {
        loginForm = new LoginForm();
        loginForm.setUserName("Username");
        loginForm.setPassword("Password123");
    }

    @Test
    public void validateUserOnLoginOKTest() {
        HashMap<String, String> map = FormValidator.validateUserOnLogin(loginForm);
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void validateUserOnLoginUsernameErrorTest() {
        loginForm.setUserName("%^&*");
        HashMap<String, String> map = FormValidator.validateUserOnLogin(loginForm);
        Assert.assertTrue(map.size() == 1);
        Assert.assertTrue(map.get("login_username_error") != null);
    }

    @Test
    public void validateUserOnLoginPasswordErrorTest() {
        loginForm.setPassword("%^&*");
        HashMap<String, String> map = FormValidator.validateUserOnLogin(loginForm);
        Assert.assertTrue(map.size() == 1);
        Assert.assertTrue(map.get("login_password_error") != null);
    }

    @Test
    public void validateUserOnLoginUsernameNullTest() {
        loginForm.setUserName(null);
        HashMap<String, String> map = FormValidator.validateUserOnLogin(loginForm);
        Assert.assertTrue(map.size() == 1);
        Assert.assertTrue(map.get("login_username_error") != null);
    }

    @Test
    public void validateUserOnLoginPasswordNullTest() {
        loginForm.setPassword(null);
        HashMap<String, String> map = FormValidator.validateUserOnLogin(loginForm);
        Assert.assertTrue(map.size() == 1);
        Assert.assertTrue(map.get("login_password_error") != null);
    }

    @Test
    public void validateUserOnLoginPasswordAndUsernameErrorTest() {
        loginForm.setUserName("%^&*");
        loginForm.setPassword("%^&*");
        HashMap<String, String> map = FormValidator.validateUserOnLogin(loginForm);
        Assert.assertTrue(map.size() == 2);
        Assert.assertTrue(map.get("login_username_error") != null);
        Assert.assertTrue(map.get("login_password_error") != null);
    }


}
