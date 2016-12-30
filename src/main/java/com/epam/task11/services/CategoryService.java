package com.epam.task11.services;

import com.epam.task11.dao.api.CategoryDAO;
import com.epam.task11.db.TransactionManager;
import com.epam.task11.entity.db.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryService {

    private static final Logger LOG = LogManager.getLogger(CategoryService.class);
    private CategoryDAO categoryDAO;
    private TransactionManager transactionManager;

    public CategoryService(CategoryDAO categoryDAO, TransactionManager transactionManager) {
        this.categoryDAO = categoryDAO;
        this.transactionManager = transactionManager;

    }

    public List<Category> getAllCategory() {
        return transactionManager.execute(connection -> categoryDAO.get());
    }

}
