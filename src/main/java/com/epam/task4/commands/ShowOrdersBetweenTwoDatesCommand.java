package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.entity.Order;
import com.epam.task4.services.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShowOrdersBetweenTwoDatesCommand implements Command {
    private OrderService orderService;
    private Scanner scanner;

    public ShowOrdersBetweenTwoDatesCommand(Scanner scanner, OrderService orderService) {
        this.scanner = scanner;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        System.out.println("Showing orders between two dates:");
        System.out.println("Print first date in format dd.MM.YYYY");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateFirstStr = null;
        String dateSecondStr = null;
        if (scanner.hasNextLine()) {
            dateFirstStr = scanner.nextLine();
        }
        System.out.println("Print second date in format dd.MM.YYYY");
        if (scanner.hasNextLine()) {
            dateSecondStr = scanner.nextLine();
        }
        try {
            Date dateFirst = simpleDateFormat.parse(dateFirstStr);
            Date dateSecond = simpleDateFormat.parse(dateSecondStr);
            if (!checkIfDatesValid(dateFirst, dateSecond)) {
                return;
            }
            List<Order> list = orderService.getOrders(dateFirst, dateSecond);
            pringList(list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfDatesValid(Date dateFirst, Date dateSecond) {
        if (dateFirst.after(dateSecond)) {
            System.out.println("First date must not be after second date");
            return false;
        }
        return true;
    }

    private void pringList(List<Order> list) {
        if (list != null) {
            for (Order order : list) {
                System.out.println(order);
            }
        } else {
            System.out.println("Nothing was found");
        }
    }
}
