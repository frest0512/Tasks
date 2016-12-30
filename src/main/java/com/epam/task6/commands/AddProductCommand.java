package com.epam.task6.commands;

import com.epam.task1.entity.Transport;
import com.epam.task4.commands.util.Command;
import com.epam.task4.services.ProductService;
import com.epam.task6.readers.Reader;
import com.epam.task6.readers.ReaderContainer;

import java.util.Scanner;

public class AddProductCommand implements Command {

    private Scanner scanner;
    private ProductService productService;
    private ReaderContainer readerContainer;

    public AddProductCommand(Scanner scanner, ProductService productService, ReaderContainer readerContainer) {
        this.scanner = scanner;
        this.productService = productService;
        this.readerContainer = readerContainer;
    }

    @Override
    public void execute() {
        System.out.println("Choose object to add:");
        readerContainer.getAll().keySet().forEach(s -> System.out.println(s + "  "));
        String key = null;
        if (scanner.hasNext()) key = scanner.next();
        if (!readerContainer.getAll().containsKey(key)) {
            System.out.println("Wrong key");
            return;
        }
        System.out.println("Enter article:");
        String article = null;
        if (scanner.hasNext()) article = scanner.next();
        Reader transportReader = readerContainer.get(key);
        Transport transport = transportReader.read();
        productService.add(article, transport);
    }
}
