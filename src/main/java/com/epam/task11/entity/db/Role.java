package com.epam.task11.entity.db;

import com.epam.task11.annotations.DBField;
import com.epam.task11.util.Constants;

public class Role {
    @DBField(DBFieldName = Constants.DB_ROLE_ID)
    private int id;
    @DBField(DBFieldName = Constants.DB_ROLE_NAME)
    private String name;
    @DBField(DBFieldName = Constants.DB_ROLE_DESCRIPTION)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
