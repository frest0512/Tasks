package com.epam.task11.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class PageCameFromFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String str = request.getServletPath();
        if(str.equals("")){
            str = "/";
        }
        request.setAttribute("pageCameFrom", str);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
