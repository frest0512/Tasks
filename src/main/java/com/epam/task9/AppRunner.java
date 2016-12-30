package com.epam.task9;

import com.epam.task4.dao.Storage;
import com.epam.task4.dao.impl.ProductDAO;
import com.epam.task4.services.ProductService;
import com.epam.task9.command.DefaultCommand;
import com.epam.task9.command.GetProductCountCommand;
import com.epam.task9.command.GetProductInfoCommand;
import com.epam.task9.command.base.Command;
import com.epam.task9.command.base.CommandContainer;
import com.epam.task9.factory.AbstractFactory;
import com.epam.task9.factory.FactoryProducer;
import com.epam.task9.factory.HTTPFactory;
import com.epam.task9.factory.TCPFactory;
import com.epam.task9.server.HTTPServer;
import com.epam.task9.server.Servers;
import com.epam.task9.server.TCPServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.epam.task4.App.initGoods;
import static com.epam.task4.App.initOrderList;

public class AppRunner {
    private static final int HTTP_PORT = 8000;
    private static final int TCP_PORT = 3000;
    private static final String HOST = "localhost";
    private ExecutorService executorServiceHTTP = Executors.newFixedThreadPool(100);

    public void start() {
        Storage storage = new Storage(initGoods(new LinkedHashMap<>()),
                new LinkedHashMap<>(),
                new LinkedHashMap<>(),
                initOrderList(new TreeMap<>()));
        ProductService productService = new ProductService(new ProductDAO(storage.getGoods()));
        FactoryProducer factoryProducer = initDirector(productService);
        CommandContainer commandContainer = initCommandContainer(factoryProducer);
        try {
            Thread httpServer = new Thread(new HTTPServer(commandContainer, HTTP_PORT));
            httpServer.start();
        } catch (IOException e) {
            System.err.println("Error in HTTP server " + e);
        }
        try {
            TCPServer tcpServer = new TCPServer(commandContainer, TCP_PORT);
            tcpServer.startServer();
        } catch (IOException e) {
            System.err.println("Error in TCP server " + e);
        }
        Client client = new Client(HOST, TCP_PORT);
        client.makeRequest();
    }

    public CommandContainer initCommandContainer(FactoryProducer factoryProducer) {
        HashMap<String, Command> commands = new HashMap<>();
        Command commandCount = new GetProductCountCommand(executorServiceHTTP, factoryProducer);
        Command info = new GetProductInfoCommand(executorServiceHTTP, factoryProducer);

        commands.put("/shop/count", commandCount);
        commands.put("/shop/item", info);

        commands.put("exist count", commandCount);
        commands.put("exist item", info);

        return new CommandContainer(commands, new DefaultCommand(executorServiceHTTP, factoryProducer));
    }

    public FactoryProducer initDirector(ProductService productService) {
        HashMap<Servers, AbstractFactory> factories = new HashMap<>();
        factories.put(Servers.HTTP, new HTTPFactory(productService));
        factories.put(Servers.TCP, new TCPFactory(productService));
        return new FactoryProducer(factories);
    }
}
