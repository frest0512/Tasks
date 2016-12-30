package com.epam.task4;

import com.epam.task1.entity.Car;
import com.epam.task1.entity.Helicopter;
import com.epam.task1.entity.Transport;
import com.epam.task4.commands.*;
import com.epam.task4.commands.util.Command;
import com.epam.task4.commands.util.Controller;
import com.epam.task4.dao.Storage;
import com.epam.task4.dao.impl.HistoryDAO;
import com.epam.task4.dao.impl.OrderDAO;
import com.epam.task4.dao.impl.ProductDAO;
import com.epam.task4.dao.impl.ShoppingCartDAO;
import com.epam.task4.entity.Order;
import com.epam.task4.services.HistoryService;
import com.epam.task4.services.OrderService;
import com.epam.task4.services.ProductService;
import com.epam.task4.services.ShoppingCartService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {


    public static void main(String[] strs) throws ParseException {
        Storage storage = new Storage(initGoods(new LinkedHashMap<>()),
                new LinkedHashMap<>(),
                new LinkedHashMap<>(),
                initOrderList(new TreeMap<>()));
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);
        controller.service(initAll(storage, scanner));

    }


    public static HashMap<String, Command> initAll(Storage storage, Scanner scanner) {
        HashMap<String, Command> commands = new HashMap<>();

        ProductDAO productDAO = new ProductDAO(storage.getGoods());
        HistoryDAO historyDAO = new HistoryDAO(storage.getHistoryGoods());
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO(storage.getShoppingCart());
        OrderDAO orderDAO = new OrderDAO(storage.getOrderList());

        ProductService productService = new ProductService(productDAO);
        HistoryService historyService = new HistoryService(historyDAO, productDAO);
        ShoppingCartService shoppingCartService = new ShoppingCartService(shoppingCartDAO, productDAO, historyDAO);
        OrderService orderService = new OrderService(orderDAO, shoppingCartDAO, productDAO);

        commands.put("1", new GetAllProductCommand(productService));
        commands.put("2", new GetLastFiveOrdersCommand(historyService));
        commands.put("3", new AddProductToShoppingCartCommand(scanner, shoppingCartService));
        commands.put("4", new ShowShoppingCartCommand(shoppingCartService));
        commands.put("5", new MakeOrderCommand(orderService));
        commands.put("6", new ClosestOrderCommand(scanner, orderService));
        commands.put("7", new ShowOrdersBetweenTwoDatesCommand(scanner, orderService));
        commands.put("ErrorCommand", new ErrorCommand());

        System.out.println("1 - GetAllProducts\n" +
                "2 - GetLastFiveOrders\n" +
                "3 - AddProductToShopingCart\n" +
                "4 - ShowShoppingCart\n" +
                "5 - MakeOrder\n" +
                "6 - ShowClosestOrder\n" +
                "7 - ShowOrdersBetweenTwoDates\n");

        return commands;
    }

    public static LinkedHashMap<String, Transport> initGoods(LinkedHashMap<String, Transport> goods) {
        goods.put("Bullet", new Car(1000, 800_000, 300, 4, 200, 4));
        goods.put("Porshe", new Car(1000, 860_000, 300, 4, 300, 4));
        goods.put("VAS", new Car(1000, 500_000, 234, 4, 150, 4));
        goods.put("LADA", new Car(1000, 1_000, 100, 4, 70, 4));

        goods.put("Apache", new Helicopter(1000, 1_000_000, 100, 100_000, 5));
        goods.put("Alegator", new Helicopter(1000, 1_200_000, 100, 200_000, 10));

        goods.put("Mitsubisi", new Transport(1000, 300_000, 353));
        goods.put("VAS", new Transport(1000, 500_000, 234));
        goods.put("Bullet", new Transport(1000, 80_000, 300));
        goods.put("LADA", new Transport(1000, 1_000, 100));
        goods.put("Bullet", new Transport(1000, 80_000, 300));
        goods.put("Mers", new Transport(1000, 2_000_000, 300));

        return goods;
    }

    public static TreeMap<Date, Order> initOrderList(TreeMap<Date, Order> orderList) {
        HashMap<String, Integer> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        map.put("iPhone 6", 6);
        map.put("iPhone 6s", 1);
        try {
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
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return orderList;
    }
}
