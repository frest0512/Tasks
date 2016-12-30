package com.epam.task6;

import com.epam.task4.commands.*;
import com.epam.task4.commands.util.Command;
import com.epam.task4.commands.util.Controller;
import com.epam.task4.dao.Storage;
import com.epam.task4.dao.impl.HistoryDAO;
import com.epam.task4.dao.impl.OrderDAO;
import com.epam.task4.dao.impl.ProductDAO;
import com.epam.task4.dao.impl.ShoppingCartDAO;
import com.epam.task4.services.HistoryService;
import com.epam.task4.services.OrderService;
import com.epam.task4.services.ProductService;
import com.epam.task4.services.ShoppingCartService;
import com.epam.task6.commands.AddProductCommand;
import com.epam.task6.readers.CarReader;
import com.epam.task6.readers.HelicopterReader;
import com.epam.task6.readers.ReaderContainer;
import com.epam.task6.recover.ShoppingCartSavingRecover;
import com.epam.task6.scanners.ConsoleScanner;
import com.epam.task6.scanners.MyScanner;
import com.epam.task6.scanners.RandomScanner;
import com.epam.task6.scanners.ScannerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.epam.task4.App.initGoods;
import static com.epam.task4.App.initOrderList;


public class App {


    public static void main(String[] strs) throws ParseException {
        Storage storage = new Storage(initGoods(new LinkedHashMap<>()),
                new LinkedHashMap<>(),
                new LinkedHashMap<>(),
                initOrderList(new TreeMap<>()));
        ShoppingCartSavingRecover shoppingCartSavingRecover = new ShoppingCartSavingRecover();

        try {
            shoppingCartSavingRecover.load(storage);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading shopping order");
        }
        Scanner scanner = new Scanner(System.in);


        Controller controller = new Controller(scanner);
        controller.service(initAll(storage, scanner));

        try {
            shoppingCartSavingRecover.save(storage);
        } catch (IOException e) {
            System.err.println("Shopping order saved");
        }

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

        commands.put("8", new AddProductCommand(scanner, productService, initReadContainer(scanner)));
        commands.put("ErrorCommand", new ErrorCommand());

        System.out.println(getCommandsListAsString());

        return commands;
    }

    public static String getCommandsListAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("0 - Exit\n")
                .append("1 - GetAllProducts\n")
                .append("2 - GetLastFiveOrders\n")
                .append("3 - AddProductToShoppingCart\n")
                .append("4 - ShowShoppingCart\n")
                .append("5 - MakeOrder\n")
                .append("6 - ShowClosestOrder\n")
                .append("7 - ShowOrdersBetweenTwoDates\n")
                .append("8 - AddProduct\n");
        return sb.toString();
    }


    public static ReaderContainer initReadContainer(Scanner scanner) {
        Random random = new Random();

        HashMap<String, MyScanner> scanners = new HashMap<>();
        scanners.put("console", new ConsoleScanner(scanner));
        scanners.put("random", new RandomScanner(random));

        ScannerFactory scannerFactory = new ScannerFactory(scanners);

        System.out.println("Введите способ добавления товара:");
        System.out.println(scanners.keySet().toString());

        String scannerKey = null;
        if (scanner.hasNext()) {
            scannerKey = scanner.next();
        }
        if (!scanners.containsKey(scannerKey)) {
            throw new NoSuchElementException();
        }
        MyScanner myScanner = scannerFactory.getScanner(scannerKey);

        ReaderContainer readerContainer = new ReaderContainer();
        readerContainer.add("car", new CarReader(myScanner));
        readerContainer.add("helicopter", new HelicopterReader(myScanner));
        return readerContainer;
    }

}

