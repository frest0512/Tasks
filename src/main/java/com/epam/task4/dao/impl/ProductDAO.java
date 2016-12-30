package com.epam.task4.dao.impl;

import com.epam.task1.entity.Transport;
import com.epam.task4.dao.api.IProductDAO;

import java.util.HashMap;

public class ProductDAO implements IProductDAO {
    private HashMap<String, Transport> products;

    public ProductDAO(HashMap<String, Transport> products) {
        this.products = products;
    }


    public HashMap<String, Transport> getAll() {
        return products;
    }


    public Transport getProductByArticle(String article) {
        return products.get(article);
    }

    public void add(String article, Transport transport) {
        products.put(article, transport);
    }

}
