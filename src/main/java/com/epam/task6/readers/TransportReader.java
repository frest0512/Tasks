package com.epam.task6.readers;

import com.epam.task1.entity.Transport;
import com.epam.task6.scanners.MyScanner;

public abstract class TransportReader extends Reader {
    protected MyScanner scanner;

    public TransportReader(MyScanner scanner) {
        this.scanner = scanner;
    }


    protected abstract Transport create();

    protected void fill(Transport transport) {
        System.out.println("Enter 'weight':");
        if (scanner.hasNextDouble()) {
            transport.setWeight(scanner.nextDouble());
        }
        System.out.println("Enter 'price':");
        if (scanner.hasNextInt()) {
            transport.setPrice(scanner.nextInt());
        }
        System.out.println("Enter 'maxSpeed':");
        if (scanner.hasNextDouble()) {
            transport.setMaxSpeed(scanner.nextDouble());
        }

    }
}
