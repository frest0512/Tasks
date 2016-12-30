package com.epam.task5.filters;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileNameFilter extends Filter {

    private static final String SEARCH_MESSAGE = "по имени";
    private static final String NAME = "name";

    public FileNameFilter(ParamsContainer paramsContainer) {
        super(paramsContainer);
        params = Arrays.asList(NAME);
        setMessage(SEARCH_MESSAGE);
    }

    @Override
    public Stream<File> doFilter(Stream<File> stream) {
        String name = paramsContainer.gerParam(NAME);

        Stream<File> newStream = stream.filter(file -> getRealName(file).equals(name));
        return (next == null) ? newStream : next.doFilter(newStream);
    }

    private String getRealName(File file) {
        int lastIndexOfDot = file.getName().lastIndexOf('.');
        return (lastIndexOfDot != -1) ? file.getName().substring(0, lastIndexOfDot) : file.getName();
    }
}
