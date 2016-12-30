package com.epam.task5.filters;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileExtensionFilter extends Filter {

    private static final String SEARCH_MESSAGE = "по разширению";
    private static final String EXTENSION = "extension";

    public FileExtensionFilter(ParamsContainer paramsContainer) {
        super(paramsContainer);
        params = Arrays.asList(EXTENSION);
        setMessage(SEARCH_MESSAGE);
    }

    @Override
    public Stream<File> doFilter(Stream<File> stream) {
        String extName = paramsContainer.gerParam(EXTENSION);
        Stream<File> newStream = stream.filter(file -> file.getName().endsWith(extName));
        return (next == null) ? newStream : next.doFilter(newStream);
    }
}
