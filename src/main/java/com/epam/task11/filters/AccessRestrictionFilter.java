package com.epam.task11.filters;

import com.epam.task11.entity.db.User;
import com.epam.task11.parser.SecurityDOM;
import com.epam.task11.parser.SecurityRestriction;
import com.epam.task11.servlets.MainPageServlet;
import com.epam.task11.util.Constants;
import com.epam.task11.util.Glob;
import com.epam.task11.util.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessRestrictionFilter implements Filter {
    private List<SecurityRestriction> restrictions;
    private String path;
    private static final Logger LOG = LogManager.getLogger(AccessRestrictionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String pathToXML = filterConfig.getInitParameter("restrictionPath");
        path = filterConfig.getServletContext().getRealPath(pathToXML);
        SecurityDOM securityDOM = new SecurityDOM();
        restrictions = securityDOM.parse(path);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("AccessRestrictionFilter started");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestedURL = request.getServletPath().length()==0?"/":request.getServletPath();
        User user = (User) request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_USER);
        LOG.debug("requestedURL = " + requestedURL);
        LOG.debug("user = " + user);
        SecurityRestriction requestRestriction = restrictions.stream().filter(restriction ->checkURL(restriction.getUrlPattern(),requestedURL)).findFirst().orElse(null);
        LOG.debug("Restriction for url = " + requestRestriction);
        LOG.debug("Check if user is ok");
        boolean isAccess = false;
        if (requestRestriction != null) {
            if (user == null) {
                isAccess = false;
            } else {
                for (String role : requestRestriction.getRole()) {
                    if (role.equals(user.getRole().getName())) {
                        isAccess = true;
                        break;
                    }
                }
            }
        } else {
            isAccess = true;
        }
        LOG.debug("User accessed = " + isAccess);
        if (isAccess) {
            filterChain.doFilter(request, response);
        } else {
            request.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER+"_no_rights_to_view_page","You have no rights to view this page");
            request.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER+"_url_to_redirect_after_login",requestedURL);
            response.sendRedirect(request.getServletContext().getContextPath() + Path.LOGIN_PAGE);
        }
    }

    private boolean checkURL(String urlPattern,String requestedURL){
        String regex = Glob.createRegexFromGlob(urlPattern);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(requestedURL);
        return m.matches();
    }
    @Override
    public void destroy() {

    }
}
