package com.epam.task4.commands.util;

import java.util.HashMap;

public class CommandContainer {
    private HashMap<String, Command> commands;

    public CommandContainer(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    public Command getCommand(String name) {
        return commands.getOrDefault(name, commands.get("ErrorCommand"));
    }
}