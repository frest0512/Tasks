package com.epam.task6.scanners;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ScannerFactory {

    private HashMap<String, MyScanner> scanners;

    public ScannerFactory(HashMap<String, MyScanner> scanners) {
        this.scanners = scanners;
    }

    public MyScanner getScanner(String key) {
        if (!scanners.containsKey(key)) {
            throw new NoSuchElementException();
        }
        return scanners.get(key);
    }
}
