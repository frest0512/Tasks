package com.epam.task5.filters;

import java.io.File;
import java.util.stream.Stream;

public class FilterContainer {
    private Filter lastFilter = null;

    public void put(Filter filter) {
        if (lastFilter != null) {
            filter.setNext(lastFilter);
        }
        lastFilter = filter;
    }

    public Stream<File> execute(Stream<File> stream) {
        return lastFilter.doFilter(stream);
    }
}
