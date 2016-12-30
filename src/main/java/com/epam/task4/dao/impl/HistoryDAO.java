package com.epam.task4.dao.impl;


import com.epam.task4.dao.api.IHistoryDAO;

import java.util.LinkedHashMap;

public class HistoryDAO implements IHistoryDAO {
    private LinkedHashMap<String, Integer> history;

    public HistoryDAO(LinkedHashMap<String, Integer> history) {
        this.history = history;
    }

    public LinkedHashMap<String, Integer> getAll() {
        return history;
    }

    public void add(String key, Integer product) {
        history.put(key, product);
    }

}
