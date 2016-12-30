package com.epam.task9.factory;

import com.epam.task9.server.Servers;

import java.util.HashMap;

public class FactoryProducer {
    private HashMap<Servers, AbstractFactory> factory;

    public FactoryProducer(HashMap<Servers, AbstractFactory> factory) {
        this.factory = factory;
    }

    public AbstractFactory getFactory(Servers servers) {
        return factory.get(servers);
    }
}
