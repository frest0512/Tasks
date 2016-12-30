package com.epam.task11.services;

import com.epam.task11.dao.api.ProductDAO;
import com.epam.task11.db.TransactionManager;
import com.epam.task11.entity.ProductSearchCriteria;
import com.epam.task11.entity.db.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private ProductDAO productDAO;
    private TransactionManager transactionManager;

    public ProductService(ProductDAO productDAO, TransactionManager transactionManager) {
        this.productDAO = productDAO;
        this.transactionManager = transactionManager;

    }

    public List<Product> getAllProducts() {
        return transactionManager.execute(connection -> productDAO.get());
    }

    public List<Product> getProducts(ProductSearchCriteria productSearchCriteria) {
        return transactionManager.execute(connection -> productDAO.get(productSearchCriteria));
    }

    public int getProductCount(ProductSearchCriteria.SearchForm searchForm) {
        return transactionManager.execute(connection -> productDAO.getCount(searchForm));
    }

    public Product getProductById(int productid) {
        return transactionManager.execute(connection -> productDAO.get(productid));
    }
}
