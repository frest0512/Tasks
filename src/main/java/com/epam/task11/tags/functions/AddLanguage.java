package com.epam.task11.tags.functions;

import java.util.Arrays;
import java.util.List;

public class AddLanguage {
    public static String addLang(String query, String o) {
        StringBuilder res = new StringBuilder();
        res.append("?");
        if (query == null || query.length() == 0) {
            return res.append(o).toString();
        }
        List<String> params = Arrays.asList(query.split("&"));
        String key = o.split("=")[0];
        params.stream().filter(s -> !s.startsWith(key)).forEach(s -> {
            res.append(s).append("&");
        });
        res.append(o);
        return res.toString();
    }
}
