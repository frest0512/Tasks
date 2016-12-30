package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.entity.Order;
import com.epam.task4.services.OrderService;

public class MakeOrderCommand implements Command {

    private OrderService orderService;

    public MakeOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        System.out.println("Making order...");
        Order order = orderService.makeOrder();
        System.out.println("Order made, order = " + order);
    }
}
