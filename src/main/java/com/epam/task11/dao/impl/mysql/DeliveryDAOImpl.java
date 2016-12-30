package com.epam.task11.dao.impl.mysql;

import com.epam.task11.dao.api.DeliveryDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Delivery;
import com.epam.task11.exeptions.DAOException;

import java.sql.*;

public class DeliveryDAOImpl implements DeliveryDAO {
    private static final String ADD_QUERY = "INSERT INTO `delivery`\n" +
            "(`city`,\n" +
            "`street`,\n" +
            "`building`,\n" +
            "`apartment`)\n" +
            "VALUES(?,?,?,?)";

    @Override
    public long add(Delivery delivery) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, delivery.getCity());
            preparedStatement.setString(2, delivery.getStreet());
            preparedStatement.setString(3, delivery.getBuilding());
            preparedStatement.setString(4, delivery.getApartment());
            int res = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (res == 1) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return -1;
    }
}
