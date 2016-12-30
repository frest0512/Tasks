package com.epam.task11.dao.api;

import com.epam.task11.entity.db.Order;

public interface OrderDAO {
    long add(Order order);
}
