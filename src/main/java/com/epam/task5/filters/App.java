package com.epam.task5.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        ParamsContainer paramsContainer = new ParamsContainer();
        FilterContainer filterContainer = new FilterContainer();
        List<Filter> list = new ArrayList<>();
        list.add(new FileDateFilter(paramsContainer));
        list.add(new FileNameFilter(paramsContainer));
        list.add(new FileSizeFilter(paramsContainer));
        list.add(new FileExtensionFilter(paramsContainer));

        FileWalker demo = new FileWalker();
        demo.start(list, filterContainer, paramsContainer);
    }
}
