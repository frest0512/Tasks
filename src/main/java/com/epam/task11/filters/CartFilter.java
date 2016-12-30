package com.epam.task11.filters;


import com.epam.task11.entity.order.ShoppingCart;
import com.epam.task11.util.Constants;
import com.epam.task11.util.Path;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/order"})
public class CartFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_CART);
        if (cart != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getServletContext().getContextPath() + Path.CART_PAGE);
        }
    }

    @Override
    public void destroy() {

    }
}
