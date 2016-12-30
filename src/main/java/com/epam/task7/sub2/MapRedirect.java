package com.epam.task7.sub2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MapRedirect implements InvocationHandler {
    private HashMap<String, Object> map;

    public MapRedirect(HashMap<String, Object> ll) {
        map = ll;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            return map.put(method.getName().substring(3, method.getName().length()), args[0]);
        } else if (method.getName().startsWith("exist")) {
            return map.get(method.getName().substring(3, method.getName().length()));
        }
        return method.invoke(map, args);
    }
}
