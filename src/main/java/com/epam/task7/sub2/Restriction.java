package com.epam.task7.sub2;

import com.epam.task7.sub2.transport.api.Transport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Restriction implements InvocationHandler {
    private Transport transport;

    public Restriction(Transport transport) {
        this.transport = transport;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            throw new IllegalAccessException();
        }
        return method.invoke(transport, args);
    }
}
