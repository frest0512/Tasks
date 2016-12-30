package com.epam.task11.services;

import com.epam.task11.dao.api.DeliveryDAO;
import com.epam.task11.dao.api.OrderDAO;
import com.epam.task11.dao.api.OrderItemDAO;
import com.epam.task11.dao.api.PaymentDAO;
import com.epam.task11.db.TransactionManager;
import com.epam.task11.entity.db.Order;
import com.epam.task11.exeptions.DAOException;

import java.sql.Connection;
import java.util.Calendar;

public class OrderService {
    private OrderDAO orderDAO;
    private DeliveryDAO deliveryDAO;
    private PaymentDAO paymentDAO;
    private OrderItemDAO orderItemDAO;
    private TransactionManager transactionManager;

    public OrderService(OrderDAO orderDAO, DeliveryDAO deliveryDAO, PaymentDAO paymentDAO, OrderItemDAO orderItemDAO, TransactionManager transactionManager) {
        this.orderDAO = orderDAO;
        this.deliveryDAO = deliveryDAO;
        this.paymentDAO = paymentDAO;
        this.orderItemDAO = orderItemDAO;
        this.transactionManager = transactionManager;
    }

    public boolean makeOrder(Order order) {
        return transactionManager.executeWithTransaction(connection -> {
            long paymentRes = paymentDAO.add(order.getPayment());
            if (paymentRes < 0) {
                throw new DAOException("Exception adding payment");
            }
            long deliveryRes = deliveryDAO.add(order.getDelivery());
            if (deliveryRes < 0) {
                throw new DAOException("Exception adding delivery");
            }
            order.getPayment().setId(paymentRes);
            order.getDelivery().setId(deliveryRes);
            order.setDate(Calendar.getInstance().getTime());
            order.setStatus("Payed");
            order.setStatusDescription(order.getUser().getUserName() + " made order");
            long orderId = orderDAO.add(order);
            if (orderId < 0) {
                throw new DAOException("Exception adding order");
            }
            order.setId(orderId);
            boolean orderItemRes = orderItemDAO.add(order);
            if (!orderItemRes) {
                throw new DAOException("Exception adding order items");
            }
            return true;
        }, Connection.TRANSACTION_READ_UNCOMMITTED);

    }
}
