package com.epam.task4.services;

import com.epam.task4.dao.impl.HistoryDAO;
import com.epam.task4.dao.impl.ProductDAO;

public class HistoryService {
    private HistoryDAO historyDAO;
    private ProductDAO productDAO;

    public HistoryService(HistoryDAO historyDAO, ProductDAO productDAO) {
        this.historyDAO = historyDAO;
        this.productDAO = productDAO;
    }

    public String getHistoryAsString() {
        StringBuilder result = new StringBuilder();
        result.append("History:\n");
        historyDAO.getAll().forEach((key, value) -> {
            result.append("[number = ").append(value).append(",readers = ").append(productDAO.getProductByArticle(key)).append("]\n");
        });
        return result.toString();
    }
}
