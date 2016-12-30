package com.epam.task11.language;

import com.epam.task11.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SessionLanguageDescriptor extends LanguageDescriptor {
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        request.getSession().setAttribute(Constants.SESSION_LOCALE, locale);
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        return (Locale) request.getSession().getAttribute(Constants.SESSION_LOCALE);
    }
}
