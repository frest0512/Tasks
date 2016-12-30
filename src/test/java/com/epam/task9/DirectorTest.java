package com.epam.task9;

import com.epam.task9.factory.AbstractFactory;
import com.epam.task9.factory.HTTPFactory;
import com.epam.task9.factory.TCPFactory;
import com.epam.task9.server.Servers;
import org.junit.Assert;
import org.junit.Test;

public class DirectorTest {

    @Test
    public void directorHTTPTest() {
        AbstractFactory factory = new AppRunner().initDirector(null).getFactory(Servers.HTTP);
        Assert.assertTrue(factory instanceof HTTPFactory);
    }

    @Test
    public void directorTCPTest() {
        AbstractFactory factory = new AppRunner().initDirector(null).getFactory(Servers.TCP);
        Assert.assertTrue(factory instanceof TCPFactory);
    }


}
