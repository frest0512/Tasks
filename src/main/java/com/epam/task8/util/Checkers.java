package com.epam.task8.util;

import com.epam.task8.entity.Interval;

import java.util.List;

public class Checkers {
    public static boolean isPrime(int num) {
        for (int k = 2; k <= num / 2; k++) {
            if (num % k == 0) {
                return false;
            }
        }
        return true;
    }

    public static void findPrime(Interval interval, List<Integer> list) {
        for (int j = interval.getBegine(); j < interval.getEnd(); j++) {
            if (isPrime(j)) {
                list.add(j);
            }
        }
    }

    public static long countOperation(Interval interval, int threadNumber) {
        double operationsDouble = (Math.pow(interval.getEnd(), 2) - Math.pow(interval.getBegine(), 2)) / 4;
        long operations = Math.round(operationsDouble);
        long intervalToOneThread = operations / threadNumber;
        return intervalToOneThread;
    }
}
