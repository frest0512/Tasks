package com.epam.task11.filters;

import com.epam.task11.compression.CustomHttpServletResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"*.css","*.js","/"})
public class CompressionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (acceptsGZipEncoding(request)) {
            response.addHeader("Content-Encoding", "gzip");
            CustomHttpServletResponse gzipResponse = new CustomHttpServletResponse(response);
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest request) {
        String encoding = request.getHeader("Accept-Encoding");
        return encoding != null && encoding.contains("gzip");
    }
}