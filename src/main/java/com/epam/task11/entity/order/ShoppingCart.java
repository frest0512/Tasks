package com.epam.task11.entity.order;

import com.epam.task11.entity.db.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product key) {
        if (items.containsKey(key)) {
            int amount = items.get(key);
            items.put(key, ++amount);
        } else {
            items.put(key, 1);
        }
    }

    public void removeItem(Product key) {
        items.remove(key);
    }

    public int size() {
        AtomicInteger total = new AtomicInteger(0);
        items.entrySet().stream().forEach(e -> total.getAndAdd(e.getValue()));
        return total.intValue();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public int totalPrice() {
        AtomicInteger total = new AtomicInteger(0);
        items.entrySet().stream().forEach(e -> total.getAndAdd(e.getKey().getPrice() * e.getValue().intValue()));
        return total.intValue();
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
