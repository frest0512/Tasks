package com.epam.task11.filters;

import com.epam.task11.language.LanguageDescriptor;
import com.epam.task11.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class LangFilter implements Filter {
    private LanguageDescriptor languageDescriptor;
    private Locale defaultLocale;
    private List<String> availableLocale;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        languageDescriptor = (LanguageDescriptor) filterConfig.getServletContext().getAttribute(Constants.LANGUAGE_DESCRIPTOR_NAME);
        String locale = filterConfig.getInitParameter(Constants.DEFAULT_LOCALE);
        defaultLocale = new Locale(locale);
        availableLocale = Arrays.asList(filterConfig.getInitParameter(Constants.AVAILABLE_LOCALES).split(","));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String paramLocale = request.getParameter("lang");
        Locale locale = null;
        if (paramLocale != null) {
            Locale loc = new Locale(paramLocale);
            if(checkIfLocaleExists(loc)){
                locale = loc;
                languageDescriptor.setLocale(request,response,locale);
            }
        }
        if (locale == null) {
            locale = languageDescriptor.getLocale(request);
        }
        if (locale == null) {
            Enumeration<Locale> locales = request.getLocales();
            while (locales.hasMoreElements()) {
                Locale loc = locales.nextElement();
                if (checkIfLocaleExists(loc)) {
                   locale = loc;
                    break;
                }
            }
        }
        if (locale == null) {
            locale = defaultLocale;
        }
        filterChain.doFilter(getHttpServletWrapper(request, locale), response);

    }

    private boolean checkIfLocaleExists(Locale locale) {
        return availableLocale.contains(locale.getLanguage());
    }

    @Override
    public void destroy() {

    }

    private HttpServletRequestWrapper getHttpServletWrapper(final HttpServletRequest req, final Locale locale) {
        return new HttpServletRequestWrapper(req) {
            @Override
            public Locale getLocale() {
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return new Enumeration<Locale>() {
                    private boolean flag = true;

                    @Override
                    public boolean hasMoreElements() {
                        return flag;
                    }

                    @Override
                    public Locale nextElement() {
                        flag = false;
                        return locale;
                    }
                };
            }
        };
    }
}
