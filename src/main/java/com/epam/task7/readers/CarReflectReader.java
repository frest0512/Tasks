package com.epam.task7.readers;

import com.epam.task1.entity.Car;
import com.epam.task1.entity.Transport;
import com.epam.task6.scanners.MyScanner;

import java.util.ResourceBundle;

public class CarReflectReader extends ReflectReader {
    public CarReflectReader(ResourceBundle rs, MyScanner scanner) {
        super(rs, scanner);
    }

    @Override
    protected Transport create() {
        return new Car();
    }
}
