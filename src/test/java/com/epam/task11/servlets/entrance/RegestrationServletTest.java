package com.epam.task11.servlets.entrance;

import com.epam.task11.captcha.handlers.CaptchaHandler;
import com.epam.task11.captcha.handlers.CookieCaptchaHandler;
import com.epam.task11.captcha.handlers.HiddenFieldCaptchaHandler;
import com.epam.task11.captcha.handlers.SessionCaptchaHandler;
import com.epam.task11.dao.impl.memory.UserDAOImpl;
import com.epam.task11.entity.db.User;
import com.epam.task11.services.UserService;
import com.epam.task11.servlets.entrance.RegisterServlet;
import com.epam.task11.util.Constants;
import com.epam.task11.util.Storage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegestrationServletTest {

    private static final User EXPECTED_RESULT = new User();

    static {
        EXPECTED_RESULT.setFirstName("Ivan");
        EXPECTED_RESULT.setLastName("Ivanov");
        EXPECTED_RESULT.setEmail("ivanov@mail.com");
        EXPECTED_RESULT.setUserName("ivanov");
        EXPECTED_RESULT.setPassword("");
        EXPECTED_RESULT.setSubscribe(true);
    }

    private RegisterServlet registerServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext context;
    private ServletConfig servletConfig;
    private Storage dataBase;

    @Before
    public void setUp() throws ServletException {
        dataBase = new Storage();
        registerServlet = new RegisterServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        context = mock(ServletContext.class);
        servletConfig = mock(ServletConfig.class);
    }

    @After
    public void tearDown() {
        registerServlet = null;
        request = null;
        response = null;
        session = null;
        context = null;
    }

    @Test
    public void registerServletTestAddNewUserBySession() throws ServletException, IOException {
        initFormParameters();
        CaptchaHandler captchaHandler = mock(SessionCaptchaHandler.class);
        when(captchaHandler.validate(any(), any())).thenReturn(true);
        initContextParameters(captchaHandler);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(request.getServletContext()).thenReturn(context);

        registerServlet.doPost(request, response);

        assertTrue(dataBase.getUsers().size() == 2);
        User result = dataBase.getUsers().stream().filter(user -> user.getEmail().equals("ivanov@mail.com")).findFirst().orElse(null);
        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void registerServletTestAddNewUserByCookie() throws ServletException, IOException {
        initFormParameters();
        CaptchaHandler captchaHandler = mock(CookieCaptchaHandler.class);
        when(captchaHandler.validate(any(), any())).thenReturn(true);
        initContextParameters(captchaHandler);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(request.getServletContext()).thenReturn(context);
        registerServlet.doPost(request, response);

        assertTrue(dataBase.getUsers().size() == 2);
        User result = dataBase.getUsers().stream().filter(user -> user.getEmail().equals("ivanov@mail.com")).findFirst().orElse(null);
        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void registerServletTestAddNewUserByHiddenInput() throws ServletException, IOException {
        initFormParameters();
        CaptchaHandler captchaHandler = mock(HiddenFieldCaptchaHandler.class);
        when(captchaHandler.validate(any(), any())).thenReturn(true);
        initContextParameters(captchaHandler);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(request.getServletContext()).thenReturn(context);
        registerServlet.doPost(request, response);

        assertTrue(dataBase.getUsers().size() == 2);
        User result = dataBase.getUsers().stream().filter(user -> user.getEmail().equals("ivanov@mail.com")).findFirst().orElse(null);
        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void registerServletTestAddNewUserWhenWrongInput() throws ServletException, IOException {
        when(request.getParameter("first_name")).thenReturn("ADs78");
        when(request.getParameter("last_name")).thenReturn("Ivanov");
        when(request.getParameter("email")).thenReturn("ivanov@mail.com");
        when(request.getParameter("username")).thenReturn("ivan");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("isSubscribe")).thenReturn("ok");
        CaptchaHandler captchaHandler = mock(SessionCaptchaHandler.class);
        when(captchaHandler.validate(any(), any())).thenReturn(true);
        initContextParameters(captchaHandler);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(request.getServletContext()).thenReturn(context);

        registerServlet.doPost(request, response);

        assertTrue(dataBase.getUsers().size() == 1);
    }

    private UserService initService() {
        List<User> container = new ArrayList<>();
        User user = new User();
        user.setUserName("Name");
        user.setEmail("stock@mail.com");
        container.add(user);
        dataBase.setContainer(container);
        UserDAOImpl userDao = new UserDAOImpl(dataBase);
        return null;
    }

    private void initFormParameters() {
        when(request.getParameter("first_name")).thenReturn("Ivan");
        when(request.getParameter("last_name")).thenReturn("Ivanov");
        when(request.getParameter("email")).thenReturn("ivanov@mail.com");
        when(request.getParameter("username")).thenReturn("ivanov");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("isSubscribe")).thenReturn("on");
    }

    private void initContextParameters(CaptchaHandler captchaService) throws ServletException {
        context.setAttribute("captcha", new HashMap<String, char[]>());
        context.setAttribute("leaveTimeOfCaptcha", new HashMap<String, Date>());
        when(context.getAttribute(Constants.USER_SERVICE_NAME)).thenReturn(initService());
        when(context.getAttribute(Constants.CAPTCHA_HANDLER_NAME)).thenReturn(captchaService);
        when(servletConfig.getServletContext()).thenReturn(context);
        registerServlet.init(servletConfig);
    }

}
