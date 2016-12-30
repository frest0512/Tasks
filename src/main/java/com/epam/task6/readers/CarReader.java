package com.epam.task6.readers;

import com.epam.task1.entity.Car;
import com.epam.task1.entity.Transport;
import com.epam.task6.scanners.MyScanner;

public class CarReader extends TransportReader {
    public CarReader(MyScanner scanner) {
        super(scanner);
    }

    @Override
    public Car create() {
        return new Car();
    }

    @Override
    protected void fill(Transport transport) {
        super.fill(transport);
        Car car = (Car) transport;
        System.out.println("Enter 'wheelNumber':");
        if (scanner.hasNextInt()) {
            car.setWheelNumber(scanner.nextInt());
        }
        System.out.println("Enter 'horsePower':");
        if (scanner.hasNextInt()) {
            car.setHorsePower(scanner.nextInt());
        }
        System.out.println("Enter 'doorsNumber':");
        if (scanner.hasNextInt()) {
            car.setDoorsNumber(scanner.nextInt());
        }
    }
}
