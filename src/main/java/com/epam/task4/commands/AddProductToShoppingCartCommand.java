package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.services.ShoppingCartService;

import java.util.Scanner;

public class AddProductToShoppingCartCommand implements Command {

    private ShoppingCartService shoppingCartService;
    private Scanner scanner;

    public AddProductToShoppingCartCommand(Scanner scanner, ShoppingCartService shoppingCartService) {
        this.scanner = scanner;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        System.out.println("DefaultTransport adding:");
        System.out.println("Enter readers article:");
        String article = null;
        int number = 0;
        if (scanner.hasNextLine()) {
            article = scanner.nextLine();
        }
        System.out.println("Print readers number:");
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        }
        shoppingCartService.addItem(article, number);
    }
}
