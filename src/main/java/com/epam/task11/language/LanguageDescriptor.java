package com.epam.task11.language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public abstract class LanguageDescriptor {
    public abstract void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);

    public abstract Locale getLocale(HttpServletRequest request);
}
