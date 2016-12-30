package com.epam.task4.dao.impl;

import com.epam.task4.dao.api.IShoppingCart;

import java.util.HashMap;

public class ShoppingCartDAO implements IShoppingCart {
    private HashMap<String, Integer> storage;

    public ShoppingCartDAO(HashMap<String, Integer> storage) {
        this.storage = storage;
    }

    public HashMap<String, Integer> getAll() {
        return storage;
    }

    public void add(String key, Integer value) {
        storage.put(key, value);
    }

    public void clear() {
        storage.clear();
    }

}
