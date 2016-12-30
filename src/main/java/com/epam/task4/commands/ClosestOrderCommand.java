package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.services.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClosestOrderCommand implements Command {
    private OrderService orderService;
    private Scanner scanner;

    public ClosestOrderCommand(Scanner scanner, OrderService orderService) {
        this.scanner = scanner;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        System.out.println("Show closest order:");
        System.out.println("Print date in format dd.MM.YYYY");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
        Date date = null;
        String line = null;
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
        }
        try {
            date = simpleDateFormat.parse(line);
            String str = orderService.getClosestOrder(date).toString();
            System.out.println((str != null) ? str : "Nothing was found");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
