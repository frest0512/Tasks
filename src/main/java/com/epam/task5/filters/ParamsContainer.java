package com.epam.task5.filters;

import java.util.HashMap;

public class ParamsContainer {
    private HashMap<String, String> params = new HashMap<>();

    public String gerParam(String s) {
        return params.get(s);
    }

    public void addParam(String key, String val) {
        params.put(key, val);
    }

}
