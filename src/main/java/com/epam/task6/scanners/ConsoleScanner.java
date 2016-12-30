package com.epam.task6.scanners;

import java.util.Scanner;

public class ConsoleScanner implements MyScanner {

    private Scanner scanner;

    public ConsoleScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    @Override
    public boolean hasNextDouble() {
        return scanner.hasNextDouble();
    }

    @Override
    public String nextString() {
        return scanner.next();
    }

    @Override
    public String next() {
        return scanner.next();
    }

    @Override
    public Integer nextInt() {
        return scanner.nextInt();
    }

    @Override
    public Double nextDouble() {
        return scanner.nextDouble();
    }
}
