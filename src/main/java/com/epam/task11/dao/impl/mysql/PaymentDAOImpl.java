package com.epam.task11.dao.impl.mysql;


import com.epam.task11.dao.api.PaymentDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Payment;
import com.epam.task11.exeptions.DAOException;

import java.sql.*;

public class PaymentDAOImpl implements PaymentDAO {
    private static final String ADD_QUERY = "INSERT INTO `payments`\n" +
            "(`card_number`,\n" +
            "`date_expires`)\n" +
            "VALUES(?,?)";

    @Override
    public long add(Payment payment) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, payment.getCartNumber());
            preparedStatement.setString(2, payment.getDateCardExpire());
            int res = preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (res == 1) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return -1;
    }
}
