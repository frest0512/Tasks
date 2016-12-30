package com.epam.task4;

import com.epam.task4.dao.impl.OrderDAO;
import com.epam.task4.dao.impl.ProductDAO;
import com.epam.task4.dao.impl.ShoppingCartDAO;
import com.epam.task4.entity.Order;
import com.epam.task4.services.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OrderServiceTest {

    OrderDAO orderDAO;
    ShoppingCartDAO shoppingCartDAO;
    ProductDAO productDAO;

    @Before
    public void setUp() {
        orderDAO = mock(OrderDAO.class);
        shoppingCartDAO = mock(ShoppingCartDAO.class);
        productDAO = mock(ProductDAO.class);

    }

    @Test
    public void getAllProductsAsStringTest() {
        Order expected = new Order(new Date(), 1000, new HashMap<>());
        when(orderDAO.getClosestOrder(any(Date.class))).thenReturn(expected);

        OrderService orderService = new OrderService(orderDAO, shoppingCartDAO, productDAO);
        Order actual = orderService.getClosestOrder(new Date());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void makeOrderTest() {
        Order expected = new Order(new Date(), 0, new HashMap<>());
        when(orderDAO.add(any(Order.class))).thenReturn(expected);
        OrderService orderService = new OrderService(orderDAO, shoppingCartDAO, productDAO);
        Order actual = orderService.makeOrder();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalPriceTest() {
        OrderService orderService = new OrderService(orderDAO, shoppingCartDAO, productDAO);
        when(shoppingCartDAO.getAll()).thenReturn(new HashMap<>());
        int sum = orderService.getTotalPrice();
        Assert.assertEquals(0, sum);
    }


}
