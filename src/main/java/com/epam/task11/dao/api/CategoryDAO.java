package com.epam.task11.dao.api;

import com.epam.task11.entity.db.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> get();
}
