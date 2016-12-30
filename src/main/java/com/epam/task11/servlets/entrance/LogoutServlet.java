package com.epam.task11.servlets.entrance;

import com.epam.task11.entity.db.User;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object obj = session.getAttribute(Constants.ATTRIBUTES_APPENDER + "_user");
            if (obj != null) {
                User user = (User) obj;
                LOG.debug("User " + user.getUserName() + " logged out");
            }
            session.invalidate();
        }
        LOG.debug("Session invalidate");
        resp.sendRedirect(req.getServletContext().getContextPath()+"/");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
