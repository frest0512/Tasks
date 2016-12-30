package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.services.ProductService;

public class GetAllProductCommand implements Command {
    private ProductService productService;

    public GetAllProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println(productService.getAllProductsAsString());
    }
}
