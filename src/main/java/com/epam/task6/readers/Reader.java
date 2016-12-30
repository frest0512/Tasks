package com.epam.task6.readers;

import com.epam.task1.entity.Transport;

public abstract class Reader {

    public final Transport read() {
        Transport t = create();
        fill(t);
        return t;
    }

    protected abstract Transport create();

    protected abstract void fill(Transport transport);
}
