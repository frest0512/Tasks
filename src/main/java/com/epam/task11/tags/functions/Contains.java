package com.epam.task11.tags.functions;

import java.util.List;

public class Contains {
    public static boolean contains(List list, String o) {
        if (list == null) {
            return false;
        }
        return list.contains(o);
    }
}
