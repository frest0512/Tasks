package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;

public class ErrorCommand implements Command {

    @Override
    public void execute() {
        System.out.println("No such command");
    }
}
