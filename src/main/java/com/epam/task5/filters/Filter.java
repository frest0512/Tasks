package com.epam.task5.filters;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public abstract class Filter {
    protected Filter next;
    protected ParamsContainer paramsContainer;
    protected List<String> params;
    protected String message;

    public Filter(ParamsContainer paramsContainer) {
        this.paramsContainer = paramsContainer;
    }

    public void setNext(Filter filter) {
        this.next = filter;
    }

    public List<String> getParams() {
        return params;
    }

    public abstract Stream<File> doFilter(Stream<File> stream);

    public void setMessage(String mes) {
        this.message = mes;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Filter[" + this.getClass().getName() + "]{" +
                "next=" + next +
                '}';
    }
}
