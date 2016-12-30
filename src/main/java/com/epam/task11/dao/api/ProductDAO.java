package com.epam.task11.dao.api;

import com.epam.task11.entity.ProductSearchCriteria;
import com.epam.task11.entity.db.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> get();

    List<Product> get(ProductSearchCriteria productSearchCriteria);

    int getCount(ProductSearchCriteria.SearchForm searchForm);

    Product get(int productid);
}
