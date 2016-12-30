package com.epam.task8;

import com.epam.task8.entity.Interval;
import com.epam.task8.sub1.ThreadSercher;
import com.epam.task8.sub2.ExecutorThreadSearch;

import java.util.Scanner;

public class FirstAndSecondAppRunner {
    public void start() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Interval interval = new Interval();
        ThreadSercher threadSercher = new ThreadSercher();
        ExecutorThreadSearch executorThreadSearch = new ExecutorThreadSearch();
        System.out.println("Print interval:");
        System.out.println("begine:");
        if (scanner.hasNext()) {
            interval.setBegine(scanner.nextInt());
        }
        System.out.println("end");
        if (scanner.hasNext()) {
            interval.setEnd(scanner.nextInt());
        }
        System.out.println(interval);
        System.out.println("Print thread number:");
        int threadNumber = 0;
        if (scanner.hasNext()) {
            threadNumber = scanner.nextInt();
        }
        validateInput(interval, threadNumber);
        executorThreadSearch.search(interval, threadNumber);
        threadSercher.search(interval, threadNumber);
    }

    private void validateInput(Interval interval, int threadNumber) {
        if (interval.getBegine() > interval.getEnd()) {
            throw new IllegalArgumentException();
        }
        if (interval.getEnd() - interval.getBegine() <= threadNumber) {
            throw new IllegalArgumentException();
        }


    }
}
