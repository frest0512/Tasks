package com.epam.task7.sub2;

import com.epam.task7.sub2.transport.api.Transport;
import com.epam.task7.sub2.transport.impl.DefaultTransport;

import java.lang.reflect.Proxy;
import java.util.HashMap;

public class Factory {

    public Transport getTransport() {
        return (Transport) Proxy.newProxyInstance(
                DefaultTransport.class.getClassLoader(),
                DefaultTransport.class.getInterfaces(),
                new Restriction(new DefaultTransport())
        );
    }

    public Transport getMap(HashMap<String, Object> map) {
        return (Transport) Proxy.newProxyInstance(DefaultTransport.class.getClassLoader(), DefaultTransport.class.getInterfaces(), new MapRedirect(map));
    }
}
