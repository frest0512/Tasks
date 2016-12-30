package com.epam.task11;

import com.epam.task11.forms.RegisterForm;
import com.epam.task11.validators.FormValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class RegisterFormValidatorTest {

    @Parameterized.Parameter(value = 0)
    public RegisterForm registrationForm;

    @Parameterized.Parameter(value = 1)
    public HashMap<String, String> expected;

    @Test
    public void testValidForm() {
        assertThat(FormValidator.validateUserOnRegister(registrationForm), is(expected));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        RegisterForm rf1 = new RegisterForm();
        rf1.setFirstName("Dima");
        rf1.setLastName("Cherkashyn");
        rf1.setEmail("mail@gmail.com");
        rf1.setUserName("Dimasik1234");
        rf1.setPassword("Pass123_4");
        rf1.setIsSubscribe("on");

        RegisterForm rf2 = new RegisterForm();
        rf2.setFirstName(null);
        rf2.setLastName(null);
        rf2.setEmail(null);
        rf2.setPassword(null);
        rf2.setIsSubscribe(null);
        rf2.setUserName(null);

        RegisterForm rf3 = new RegisterForm();
        rf3.setFirstName("Dimas4");
        rf3.setLastName("Cherkashyn5");
        rf3.setUserName("Dimasik1%^&*(");
        rf3.setEmail("mail%gmail.com");
        rf3.setPassword("pass<.");

        RegisterForm rf4 = new RegisterForm();
        rf4.setFirstName("Dimas4");
        rf4.setLastName("Cherkashyn");
        rf4.setUserName("Dimasik1%^&*(");
        rf4.setEmail("mail@gmail.com");
        rf4.setPassword("pass<.");

        RegisterForm rf5 = new RegisterForm();
        rf5.setFirstName("Dimas");
        rf5.setLastName("Cherkashyn5");
        rf5.setUserName("Dimasik");
        rf5.setEmail("mail%^gmail.com");
        rf5.setPassword("pass1234_4");

        HashMap<String, String> totalError = new HashMap<>();
        totalError.put("firstName", "firstName error");
        totalError.put("lastName", "lastName error");
        totalError.put("userName", "userName error");
        totalError.put("email", "email error");
        totalError.put("password", "password error");

        HashMap<String, String> partError1 = new HashMap<>();
        partError1.put("firstName", "firstName error");
        partError1.put("userName", "userName error");
        partError1.put("password", "password error");

        HashMap<String, String> partError2 = new HashMap<>();
        partError2.put("lastName", "lastName error");
        partError2.put("email", "email error");
        return Arrays.asList(new Object[][]{
                {rf1, new HashMap<String, String>()},
                {rf2, totalError},
                {rf3, totalError},
                {rf4, partError1},
                {rf5, partError2}
        });
    }
}
