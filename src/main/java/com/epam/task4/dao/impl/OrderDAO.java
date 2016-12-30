package com.epam.task4.dao.impl;

import com.epam.task4.dao.api.IOrderDAO;
import com.epam.task4.entity.Order;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class OrderDAO implements IOrderDAO {
    private TreeMap<Date, Order> orders;

    public OrderDAO(TreeMap<Date, Order> orders) {
        this.orders = orders;
    }

    public Order add(Order order) {
        return orders.put(order.getDate(), order);
    }

    public TreeMap<Date, Order> getAll() {
        return orders;
    }

    @Override
    public List<Order> getOrders(Date from, Date to) {
        List<Order> list = new LinkedList<>();
        orders.forEach((date, order) -> {
            if (date.after(from) && date.before(to)) {
                list.add(order);
            }
        });
        return list;
    }

    @Override
    public Order getClosestOrder(Date point) {
        Date floorKey = orders.floorKey(point);
        Date higherKey = orders.higherKey(point);
        if (floorKey == null || higherKey == null) {
            return null;
        }
        long longVar1 = point.getTime() - floorKey.getTime();
        long longVar2 = point.getTime() - higherKey.getTime();
        return (longVar1 < longVar2) ? orders.get(floorKey) : orders.get(higherKey);
    }
}
