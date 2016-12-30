package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.services.ShoppingCartService;

public class ShowShoppingCartCommand implements Command {
    private ShoppingCartService shoppingCartService;

    public ShowShoppingCartCommand(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        System.out.println(shoppingCartService.getShoppingCartAsString());
    }
}
