package com.epam.task11.parser;

import java.util.List;

public class SecurityRestriction {
    private String urlPattern;
    private List<String> role;

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }


    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public List<String> getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "SecurityRestriction{" +
                "urlPattern='" + urlPattern + '\'' +
                ", role=" + role +
                '}';
    }
}
