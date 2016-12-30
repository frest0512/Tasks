package com.epam.task5.filters;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileDateFilter extends Filter {


    private static final String LOW_DATE = "lowDate";
    private static final String HIGHER_DATE = "higherDate";
    private static final String SEARCH_MESSAGE = "по дате";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public FileDateFilter(ParamsContainer paramsContainer) {
        super(paramsContainer);
        params = Arrays.asList(LOW_DATE, HIGHER_DATE);
        setMessage(SEARCH_MESSAGE);
    }

    @Override
    public Stream<File> doFilter(Stream<File> stream) {
        try {
            Date lowDate = simpleDateFormat.parse(paramsContainer.gerParam(LOW_DATE));
            Date higherDate = simpleDateFormat.parse(paramsContainer.gerParam(HIGHER_DATE));
            Stream<File> newStream = stream.filter(filterByDate(lowDate, higherDate));
            return (next == null) ? newStream : next.doFilter(newStream);
        } catch (ParseException e) {
            System.err.println(e);
        }
        return stream;
    }

    private Predicate<File> filterByDate(Date lowDate, Date higherDate) {
        return file -> new Date(file.lastModified()).after(lowDate) && new Date(file.lastModified()).before(higherDate);
    }
}
