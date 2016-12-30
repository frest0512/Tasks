package com.epam.task4;

import com.epam.task4.dao.impl.OrderDAO;
import com.epam.task4.entity.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class OrderDAOTest {
    private OrderDAO orderDAO;

    @Before
    public void setUp() throws ParseException {
        TreeMap<Date, Order> orderList = new TreeMap<>();
        HashMap<String, Integer> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        map.put("iPhone 6", 6);
        map.put("iPhone 6s", 1);
        Order order = new Order(simpleDateFormat.parse("21.03.2016"), 5601, map);
        Order order1 = new Order(simpleDateFormat.parse("21.06.2016"), 5602, map);
        Order order2 = new Order(simpleDateFormat.parse("21.07.2016"), 5603, map);
        Order order3 = new Order(simpleDateFormat.parse("21.08.1996"), 5604, map);
        Order order4 = new Order(simpleDateFormat.parse("21.09.2001"), 5605, map);
        Order order5 = new Order(simpleDateFormat.parse("21.10.1999"), 5606, map);


        orderList.put(order.getDate(), order);
        orderList.put(order1.getDate(), order1);
        orderList.put(order2.getDate(), order2);
        orderList.put(order3.getDate(), order3);
        orderList.put(order4.getDate(), order4);
        orderList.put(order5.getDate(), order5);


        orderDAO = new OrderDAO(orderList);

    }

    @Test
    public void getOrdersBetweenDatesTest() throws ParseException {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("iPhone 6", 6);
        map.put("iPhone 6s", 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Order order = new Order(simpleDateFormat.parse("21.08.1996"), 5604, map);
        Date dateBegine = simpleDateFormat.parse("20.03.1995");
        Date dateEnd = simpleDateFormat.parse("21.03.1997");
        List<Order> orders = orderDAO.getOrders(dateBegine, dateEnd);
        System.out.println(order);
        Assert.assertTrue(orders.size() == 1);
        Assert.assertEquals(orders.get(0), order);
    }
}
