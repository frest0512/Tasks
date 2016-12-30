package com.epam.task11.entity.db;

import com.epam.task11.annotations.DBField;
import com.epam.task11.util.Constants;

public class Category {
    @DBField(DBFieldName = Constants.DB_CATEGORY_ID)
    private int id;
    @DBField(DBFieldName = Constants.DB_CATEGORY_NAME)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
