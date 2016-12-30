package com.epam.task4.dao.api;

import java.util.HashMap;

public interface IShoppingCart {
    HashMap<String, Integer> getAll();

    void add(String key, Integer value);

    void clear();
}
