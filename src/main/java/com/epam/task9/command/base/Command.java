package com.epam.task9.command.base;

import com.epam.task9.server.Servers;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public abstract class Command {
    protected ExecutorService executorService;

    public Command(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public abstract void execute(Servers server, String path, Socket socket) throws IOException;
}
