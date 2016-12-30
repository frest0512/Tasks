package com.epam.task4.services;

import com.epam.task4.dao.impl.OrderDAO;
import com.epam.task4.dao.impl.ProductDAO;
import com.epam.task4.dao.impl.ShoppingCartDAO;
import com.epam.task4.entity.Order;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class OrderService {

    private OrderDAO orderDAO;
    private ShoppingCartDAO shoppingCartDAO;
    private ProductDAO productDAO;

    public OrderService(OrderDAO orderDAO, ShoppingCartDAO shoppingCartDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.shoppingCartDAO = shoppingCartDAO;
        this.productDAO = productDAO;
    }

    public Order makeOrder() {
        Order order = new Order(Calendar.getInstance().getTime(), getTotalPrice(), shoppingCartDAO.getAll());
        Order orderAdded = orderDAO.add(order);
        if (!order.equals(orderAdded)) {
            return null;
        }
        shoppingCartDAO.clear();
        return order;
    }

    public int getTotalPrice() {
        AtomicInteger result = new AtomicInteger();
        shoppingCartDAO.getAll().forEach((key, val) -> {
            result.set(result.get() + productDAO.getProductByArticle(key).getPrice() * val);
        });
        return result.get();
    }

    public List<Order> getOrders(Date from, Date to) {
        return orderDAO.getOrders(from, to);
    }

    public Order getClosestOrder(Date point) {
        return orderDAO.getClosestOrder(point);
    }
}
