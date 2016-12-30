package com.epam.task11.dao.impl.mysql;

import com.epam.task11.dao.api.OrderItemDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Order;
import com.epam.task11.entity.order.OrderedProduct;
import com.epam.task11.exeptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAOImpl implements OrderItemDAO {
    private static final String ADD_QUERY = "INSERT INTO `epam_task12`.`order_positions`\n" +
            "(`orderid`,\n" +
            "`productid`,\n" +
            "`now_price`,\n" +
            "`amount`)\n" +
            "VALUES\n" +
            "(?,?,?,?);\n";

    @Override
    public boolean add(Order order) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY)) {
            for (OrderedProduct product : order.getProducts()) {
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setInt(2, product.getProduct().getId());
                preparedStatement.setInt(3, product.getOrderedPrice());
                preparedStatement.setInt(4, product.getAmount());
                preparedStatement.addBatch();
            }
            int[] res = preparedStatement.executeBatch();
            if (res.length == order.getProducts().size()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return false;
    }
}
