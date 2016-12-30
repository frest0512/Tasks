package com.epam.task4.dao.api;

import com.epam.task4.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public interface IOrderDAO {
    Order add(Order order);

    TreeMap<Date, Order> getAll();

    List<Order> getOrders(Date from, Date to);

    Order getClosestOrder(Date point);

}
