package com.epam.task6.scanners;

import java.util.Random;

public class RandomScanner implements MyScanner {
    private Random random;

    public RandomScanner(Random random) {
        this.random = random;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public boolean hasNextInt() {
        return true;
    }

    @Override
    public boolean hasNextDouble() {
        return true;
    }

    @Override
    public String nextString() {
        return "Name" + random.nextInt();
    }

    @Override
    public String next() {
        return "" + random.nextInt();
    }

    @Override
    public Integer nextInt() {
        return random.nextInt();
    }

    @Override
    public Double nextDouble() {
        return random.nextDouble();
    }
}
