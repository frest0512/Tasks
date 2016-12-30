package com.epam.task4.services;

import com.epam.task4.dao.impl.HistoryDAO;
import com.epam.task4.dao.impl.ProductDAO;
import com.epam.task4.dao.impl.ShoppingCartDAO;

public class ShoppingCartService {

    private ShoppingCartDAO shoppingCartDAO;
    private ProductDAO productDAO;
    private HistoryDAO historyDAO;

    public ShoppingCartService(ShoppingCartDAO shoppingCartDAO, ProductDAO productDAO, HistoryDAO historyDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
        this.productDAO = productDAO;
        this.historyDAO = historyDAO;
    }

    public void addItem(String key, Integer value) {

        shoppingCartDAO.add(key, value);
        historyDAO.add(key, value);
    }


    public String getShoppingCartAsString() {
        StringBuilder result = new StringBuilder();
        result.append("Shopping order:\n");
        shoppingCartDAO.getAll().forEach((key, val) -> {
            result.append("[number = ").append(val).append(",readers = ").append(productDAO.getProductByArticle(key)).append("]\n");
        });
        return result.toString();
    }
}
