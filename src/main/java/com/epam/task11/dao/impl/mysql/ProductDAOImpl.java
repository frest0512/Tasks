package com.epam.task11.dao.impl.mysql;

import com.epam.task11.dao.api.ProductDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.db.QueryProvider;
import com.epam.task11.entity.ProductSearchCriteria;
import com.epam.task11.entity.db.Manufacturer;
import com.epam.task11.entity.db.Product;
import com.epam.task11.exeptions.DAOException;
import com.epam.task11.services.UserService;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.task11.dao.utils.Mapper.mapp;

public class ProductDAOImpl implements ProductDAO {
    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private static final String ALL_PRODUCTS_QUERY = "SELECT \n" +
            "p.idproduct,\n" +
            "p.name,\n" +
            "p.Price,\n" +
            "p.description,\n" +
            "p.image_first,\n" +
            "p.image_second,\n" +
            "m.idmanufacturer,\n" +
            "m.name \n" +
            "FROM products p inner join manufacturers m on p.idmanufacturer = m.idmanufacturer";
    private static final String PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE idproduct = ?";

    @Override
    public List<Product> get() {
        Connection connection = ConnectionThreadLocal.getConnection();
        List<Product> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ALL_PRODUCTS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(unmapReflect(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<Product> get(ProductSearchCriteria productSearchCriteria) {
        QueryProvider queryBuilder = new QueryProvider();
        Connection connection = ConnectionThreadLocal.getConnection();
        List<Product> result = new ArrayList<>();
        String query = "SELECT * FROM products " + queryBuilder.build(productSearchCriteria);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            List<Object> params = queryBuilder.params();
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(unmapReflect(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public int getCount(ProductSearchCriteria.SearchForm searchForm) {
        QueryProvider queryBuilder = new QueryProvider();
        Connection connection = ConnectionThreadLocal.getConnection();
        String query = "SELECT count(*) count FROM products " + queryBuilder.build(searchForm);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            List<Object> params = queryBuilder.params();
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(Constants.DB_COUNT);
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        }
        return -1;
    }

    @Override
    public Product get(int productid) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, productid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return unmapReflect(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        }
        return null;
    }

    private Product unmapReflect(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        Manufacturer manufacturer = new Manufacturer();
        Class<Product> productClass = Product.class;
        Field[] fields = productClass.getDeclaredFields();
        mapp(fields, product, resultSet);
        Class<Manufacturer> manufacturerClass = Manufacturer.class;
        fields = manufacturerClass.getDeclaredFields();
        mapp(fields, manufacturer, resultSet);
        product.setManufacturer(manufacturer);
        return product;
    }


}
