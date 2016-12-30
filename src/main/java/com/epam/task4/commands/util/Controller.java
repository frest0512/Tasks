package com.epam.task4.commands.util;

import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    private Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }

    public void service(HashMap<String, Command> map) {
        CommandContainer commandContainer = new CommandContainer(map);

        while (true) {
            System.out.println("Waiting for your command:");
            String commandName = null;
            if (scanner.hasNext()) {
                commandName = scanner.next();
            }
            if (commandName.equals("0")) {
                break;
            }
            Command command = commandContainer.getCommand(commandName);
            command.execute();
            System.out.println("\n\n\n\n\n");
        }
    }

}
