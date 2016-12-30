package com.epam.task11.dao.impl.mysql;

import com.epam.task11.dao.api.CategoryDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Category;
import com.epam.task11.exeptions.DAOException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.task11.dao.utils.Mapper.mapp;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String ALL_CATEGORY_QUERY = "SELECT * FROM categories";

    @Override
    public List<Category> get() {
        Connection connection = ConnectionThreadLocal.getConnection();
        ArrayList<Category> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ALL_CATEGORY_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(unmapReflect(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }

    private Category unmapReflect(ResultSet resultSet) {
        Category category = new Category();
        Class<Category> clazz = Category.class;
        Field[] fileds = clazz.getDeclaredFields();
        mapp(fileds, category, resultSet);
        return category;
    }
}
