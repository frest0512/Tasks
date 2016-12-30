package com.epam.task4.services;

import com.epam.task1.entity.Transport;
import com.epam.task4.dao.impl.ProductDAO;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public String getAllProductsAsString() {
        final StringBuilder result = new StringBuilder();
        result.append("Products:\n");
        productDAO.getAll().forEach((key, val) -> {
            result.append("[article = ")
                    .append(key)
                    .append(", readers = ")
                    .append(val)
                    .append("]\n");

        });
        return result.toString();
    }

    public Transport getTransport(String string) {
        return productDAO.getProductByArticle(string);
    }

    public int getCount() {
        return productDAO.getAll().size();
    }

    public void add(String string, Transport transport) {
        productDAO.add(string, transport);
    }
}
