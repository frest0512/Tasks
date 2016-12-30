package com.epam.task6.readers;

import java.util.HashMap;

public class ReaderContainer {
    private HashMap<String, Reader> readers = new HashMap<>();

    public void add(String string, Reader reader) {
        readers.put(string, reader);
    }

    public Reader get(String string) {
        return readers.get(string);
    }

    public HashMap<String, Reader> getAll() {
        return readers;
    }
}
