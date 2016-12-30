package com.epam.task7.readers;

import com.epam.task1.entity.Helicopter;
import com.epam.task1.entity.Transport;
import com.epam.task6.scanners.MyScanner;

import java.util.ResourceBundle;

public class HelicopterReflectReader extends ReflectReader {
    public HelicopterReflectReader(ResourceBundle rs, MyScanner scanner) {
        super(rs, scanner);
    }

    @Override
    protected Transport create() {
        return new Helicopter();
    }
}