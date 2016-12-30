package com.epam.task11;

import com.epam.task11.filters.LangFilter;
import com.epam.task11.language.LanguageDescriptor;
import com.epam.task11.language.SessionLanguageDescriptor;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LanguageFilterTest {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private FilterChain filterChain = mock(FilterChain.class);
    private FilterConfig config = mock(FilterConfig.class);
    private ServletContext servletContext = mock(ServletContext.class);
    private HttpSession session = mock(HttpSession.class);
    private LanguageDescriptor storeStrategy = mock(SessionLanguageDescriptor.class);
    private Enumeration enumeration = mock(Enumeration.class);

    @Test
    public void langParamExist() throws IOException, ServletException {
        LangFilter languageFilter = new LangFilter();
        setInitParam(languageFilter);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("ru");

        languageFilter.doFilter(request, response, (request1, response1) -> assertEquals("ru", request1.getLocale().toString()));
    }

    @Test
    public void langParamFromCookieOrSession() throws IOException, ServletException {
        LangFilter languageFilter = new LangFilter();
        setInitParam(languageFilter);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn("ru");
        when(storeStrategy.getLocale(request)).thenReturn(new Locale("ru"));

        languageFilter.doFilter(request, response, (request1, response1) -> assertEquals("ru", request1.getLocale().toString()));
    }

    @Test
    public void defaultLang() throws IOException, ServletException {
        LangFilter languageFilter = new LangFilter();
        setInitParam(languageFilter);

        when(request.getSession()).thenReturn(session);
        when(request.getLocales()).thenReturn(enumeration);

        languageFilter.doFilter(request, response, (request1, response1) -> assertEquals("en", request1.getLocale().toString()));
    }


    private void setInitParam(LangFilter languageFilter) throws ServletException {
        when(config.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("lang_list")).thenReturn("en ru");
        when(config.getInitParameter("default")).thenReturn("en");
        when(config.getInitParameter("store")).thenReturn("session");

        languageFilter.init(config);
    }
}
