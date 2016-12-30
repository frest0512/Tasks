package com.epam.task11.dao.impl.mysql;


import com.epam.task11.dao.api.OrderDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Order;
import com.epam.task11.exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger LOG = LogManager.getLogger(OrderDAOImpl.class);
    private static final String INSERT_ORDER = "INSERT INTO `order`\n" +
            "(`userid`,\n" +
            "`order_time`,\n" +
            "`deliveryId`,\n" +
            "`paymentId`,\n" +
            "`status`,\n" +
            "`status_description`)\n" +
            "VALUES\n" +
            "(?,?,?,?,?,?);\n";


    @Override
    public long add(Order order) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUser().getId());
            preparedStatement.setDate(2, new Date(order.getDate().getTime()));
            preparedStatement.setLong(3, order.getDelivery().getId());
            preparedStatement.setLong(4, order.getPayment().getId());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getStatusDescription());
            int res = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (res == 1) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        }
        return -1;
    }
}
