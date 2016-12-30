package com.epam.task9;

import com.epam.task9.command.GetProductCountCommand;
import com.epam.task9.command.GetProductInfoCommand;
import com.epam.task9.command.base.Command;
import com.epam.task9.command.base.CommandContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandContainerTest {
    private CommandContainer container;

    @Before
    public void setUp() {
        container = new AppRunner().initCommandContainer(null);
    }

    @Test
    public void getCountCommandTCPTest() {

        Command command = container.getCommand("exist count");
        Assert.assertTrue(command instanceof GetProductCountCommand);
    }

    @Test
    public void getItemCommandTCPTest() {

        Command command = container.getCommand("exist item");
        Assert.assertTrue(command instanceof GetProductInfoCommand);
    }

    @Test
    public void getCountCommandHTTPTest() {

        Command command = container.getCommand("/shop/count");
        Assert.assertTrue(command instanceof GetProductCountCommand);
    }

    @Test
    public void getItemCommandHTTPTest() {

        Command command = container.getCommand("/shop/item");
        Assert.assertTrue(command instanceof GetProductInfoCommand);
    }
}
