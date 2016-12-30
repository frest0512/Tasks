package com.epam.task5.filters;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileSizeFilter extends Filter {

    private static final String SEARCH_MESSAGE = "по размеру (KB)";
    private static final String LOW_SIZE = "lowSize";
    private static final String HIGHER_SIZE = "higherSize";

    public FileSizeFilter(ParamsContainer paramsContainer) {
        super(paramsContainer);
        params = Arrays.asList(LOW_SIZE, HIGHER_SIZE);
        setMessage(SEARCH_MESSAGE);
    }

    @Override
    public Stream<File> doFilter(Stream<File> stream) {
        int lowSize = Integer.parseInt(paramsContainer.gerParam(LOW_SIZE));
        int higherSize = Integer.parseInt(paramsContainer.gerParam(HIGHER_SIZE));
        Stream<File> newStream = stream.filter(file -> file.length() / 1024 > lowSize && file.length() / 1024 < higherSize);
        return (next == null) ? newStream : next.doFilter(newStream);
    }
}
