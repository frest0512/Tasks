package com.epam.task4.dao.api;

import java.util.LinkedHashMap;


public interface IHistoryDAO {

    LinkedHashMap<String, Integer> getAll();

    void add(String key, Integer product);
}
