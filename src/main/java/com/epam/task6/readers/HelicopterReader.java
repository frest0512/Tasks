package com.epam.task6.readers;

import com.epam.task1.entity.Helicopter;
import com.epam.task1.entity.Transport;
import com.epam.task6.scanners.MyScanner;

public class HelicopterReader extends TransportReader {
    public HelicopterReader(MyScanner scanner) {
        super(scanner);
    }


    @Override
    public Helicopter create() {
        return new Helicopter();
    }

    @Override
    protected void fill(Transport transport) {
        super.fill(transport);
        Helicopter helicopter = (Helicopter) transport;
        System.out.println("Enter 'capacity':");
        if (scanner.hasNextInt()) {
            helicopter.setCapacity(scanner.nextInt());
        }

        System.out.println("Enter 'maxFlyHight':");
        if (scanner.hasNextInt()) {
            helicopter.setMaxFlyHigh(scanner.nextInt());
        }
    }
}
