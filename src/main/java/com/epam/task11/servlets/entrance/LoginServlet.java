package com.epam.task11.servlets.entrance;

import com.epam.task11.entity.db.User;
import com.epam.task11.forms.LoginForm;
import com.epam.task11.forms.creators.FormCreator;
import com.epam.task11.services.UserService;
import com.epam.task11.util.Constants;
import com.epam.task11.util.Path;
import com.epam.task11.validators.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(LoginServlet.class);
    private UserService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UserService) config.getServletContext().getAttribute(Constants.USER_SERVICE_NAME);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Forwarding to login page");
        reSaveParams(req);
        req.getRequestDispatcher(Path.LOGIN_PAGE_SOURCE).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("LoginServlet started");
        LOG.debug("Getting params");
        LoginForm loginForm = FormCreator.createLoginForm(req);
        String url = req.getParameter(Constants.REDIRECTED_URL);
        LOG.debug("Params saved");
        LOG.debug("Validating params");
        HashMap<String, String> val = FormValidator.validateUserOnLogin(loginForm);
        LOG.debug("Params validated res = " + val.toString());
        LOG.debug("User = " + loginForm);
        if (val.size() != 0) {
            LOG.debug("Redirecting to login page with validation error");
            req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages", val);
            setErrorUserAndRedirect(req, resp, loginForm);
            return;
        }
        if (!service.isUserExist(loginForm.getUserName())) {
            LOG.debug("Redirecting to login page, user does not exist");
            req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_not_exist", "User does not exist");
            setErrorUserAndRedirect(req, resp, loginForm);
            return;
        }
        LOG.debug("Try to log user = " + loginForm);
        User user = service.getUserByLoginAndPassword(loginForm.getUserName(), loginForm.getPassword());
        if (user == null) {
            LOG.debug("Redirecting to login page, user wrong password");
            User userLocal = new User();
            userLocal.setUserName(loginForm.getUserName());
            boolean res = service.incrementAndBan(userLocal);
            LOG.debug("Incrementing fail count = "+res);
            req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_wrong_password", "Wrong password");
            setErrorUserAndRedirect(req, resp, loginForm);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MINUTE,-30);
            if(user.getDateBan()!=null && user.getDateBan().after(calendar.getTime())){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm dd.MM.yyyy");
                LOG.debug("User is blocked");
                Calendar cal = Calendar.getInstance();
                cal.setTime(user.getDateBan());
                cal.add(Calendar.MINUTE,30);
                req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_ban", "User has been bloked");
                req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_unban_time", simpleDateFormat.format(cal.getTime()));
                setErrorUserAndRedirect(req, resp, loginForm);
                return;
            }else {
                LOG.debug("Redirecting to main page, user exists, user successfully logged");
                if(user.getDateBan()!=null && user.getDateBan().before(calendar.getTime())) {
                    boolean res = service.unblockUser(user);
                    LOG.debug("Unblock user = "+res);
                }
                user.setPassword("");
                req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_USER, user);
                if(url!=null) {
                    LOG.debug("Redirecting to custom utl = "+url);
                    resp.sendRedirect(req.getServletContext().getContextPath() + url);
                } else {
                    resp.sendRedirect(req.getServletContext().getContextPath() + Path.MAIN_PAGE);
                }
            }
        }

        LOG.debug("LoginServlet finished");
    }

    public void setErrorUserAndRedirect(HttpServletRequest req, HttpServletResponse resp, LoginForm loginForm) throws IOException {
        loginForm.setPassword("");
        req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail", loginForm);
        resp.sendRedirect(req.getServletContext().getContextPath() + Path.LOGIN_PAGE);
    }

    public void reSaveParams(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_validation_error_messages");
            LOG.debug("Saved error messages from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_fail");
            LOG.debug("Saved _user_fail from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_not_exist");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_not_exist", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_not_exist");
            LOG.debug("Saved message _user_not_exist from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_wrong_password");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_wrong_password", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_wrong_password");
            LOG.debug("Saved message _user_wrong_password from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_no_rights_to_view_page");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_no_rights_to_view_page", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_no_rights_to_view_page");
            LOG.debug("Saved message _no_rights_to_view_page from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_url_to_redirect_after_login");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_url_to_redirect_after_login", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_url_to_redirect_after_login");
            LOG.debug("Saved message _url_to_redirect_after_login from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_unban_time");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_unban_time", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_unban_time");
            LOG.debug("Saved message _user_unban_time from session to request");
        }
        object = request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + "_user_ban");
        if (object != null) {
            request.setAttribute(Constants.ATTRIBUTES_APPENDER + "_user_ban", object);
            request.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + "_user_ban");
            LOG.debug("Saved message _user_ban from session to request");
        }
    }
}
