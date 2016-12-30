package com.epam.task11.servlets.entrance;

import com.epam.task11.captcha.handlers.CaptchaHandler;
import com.epam.task11.entity.db.User;
import com.epam.task11.forms.RegisterForm;
import com.epam.task11.forms.creators.FormCreator;
import com.epam.task11.services.UserService;
import com.epam.task11.util.Constants;
import com.epam.task11.util.Path;
import com.epam.task11.validators.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService service;
    private CaptchaHandler captchaHandler;
    private static final Logger LOG = LogManager.getLogger(RegisterServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UserService) config.getServletContext().getAttribute(Constants.USER_SERVICE_NAME);
        captchaHandler = (CaptchaHandler) config.getServletContext().getAttribute(Constants.CAPTCHA_HANDLER_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Forwarding to register page");
        captchaHandler.createAndSave(req, resp);
        reSaveParams(req);
        req.getRequestDispatcher(Path.REGISTER_PAGE_SOURCE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("RegisterServlet started");
        RegisterForm registerForm = FormCreator.createRegisterForm(req);
        LOG.debug("RegisterForm = " + registerForm);
        LOG.debug("Validating params");
        HashMap<String, String> val = FormValidator.validateUserOnRegister(registerForm);
        LOG.debug("Params validated res = " + val.toString());
        if (val.size() != 0) {
            LOG.debug("Redirecting to register page with validation error");
            req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages", val);
            setErrorUserAndRedirect(req, resp, registerForm);
        } else if (!captchaHandler.validate(req, registerForm.getCaptcha())) {
            LOG.debug("Redirecting to register page with captcha confirm error");
            req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_wrong_captcha", "Wrong captcha");
            setErrorUserAndRedirect(req, resp, registerForm);
        } else if (checkIfUserExist(service, registerForm.getUserName())) {
            LOG.debug("Redirecting to register page, user already exist");
            req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_exist", "User already exist");
            setErrorUserAndRedirect(req, resp, registerForm);
        } else {
            LOG.debug("All is OK, saving user");
            User user = registerForm.createUserFromForm();
            User res = service.saveUser(user);
            if (res!=null) {
                LOG.debug("Registered is " + user);
                user.setPassword("");
                req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_USER, res);
                resp.sendRedirect(req.getServletContext().getContextPath() + Path.MAIN_PAGE);
            } else {
                LOG.debug("User is registered");
                req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_error_register_message", "Problem register user");
                setErrorUserAndRedirect(req, resp, registerForm);
            }
        }
        LOG.debug("RegisterServlet finished");
    }

    public void setErrorUserAndRedirect(HttpServletRequest req, HttpServletResponse resp, RegisterForm registerForm) throws IOException {
        registerForm.setPassword("");
        req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail", registerForm);
        resp.sendRedirect(req.getServletContext().getContextPath() + Path.REGISTER_PAGE);
    }

    public boolean checkIfUserExist(UserService service, String login) {
        return service.isUserExist(login);
    }


    public void reSaveParams(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_error_register_message");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_error_register_message", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_error_register_message");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_wrong_captcha");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_wrong_captcha", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_wrong_captcha");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_exist");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_exist", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_exist");
        }
    }
}
