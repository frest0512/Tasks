package com.epam.task4.dao.api;

import com.epam.task1.entity.Transport;

import java.util.HashMap;

public interface IProductDAO {
    HashMap<String, Transport> getAll();

    Transport getProductByArticle(String article);
}
