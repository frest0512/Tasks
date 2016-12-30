package com.epam.task9.command;

import com.epam.task9.command.base.Command;
import com.epam.task9.factory.AbstractFactory;
import com.epam.task9.factory.FactoryProducer;
import com.epam.task9.server.Servers;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class GetProductInfoCommand extends Command {
    private FactoryProducer factoryProducer;

    public GetProductInfoCommand(ExecutorService executorService, FactoryProducer factoryProducer) {
        super(executorService);
        this.factoryProducer = factoryProducer;
    }

    @Override
    public void execute(Servers server, String string, Socket socket) {
        AbstractFactory factory = factoryProducer.getFactory(server);
        executorService.submit(factory.getProductInfoThread(string, socket));
    }
}
