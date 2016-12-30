package com.epam.task11.language;

import com.epam.task11.util.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CookieLanguageDescriptor extends LanguageDescriptor {
    private int timeCookieLive;

    public CookieLanguageDescriptor(int timeCookieLive) {
        this.timeCookieLive = timeCookieLive;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie(Constants.COOKIE_LOCALE, locale.getDisplayLanguage());
        cookie.setMaxAge(timeCookieLive);
        response.addCookie(cookie);
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constants.COOKIE_LOCALE)) {
                return new Locale(cookie.getValue());
            }
        }
        return null;
    }
}
