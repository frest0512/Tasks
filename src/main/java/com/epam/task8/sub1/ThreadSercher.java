package com.epam.task8.sub1;

import com.epam.task8.entity.Interval;

import java.util.ArrayList;
import java.util.List;

import static com.epam.task8.util.Checkers.countOperation;

public class ThreadSercher {
    public List<Integer> search(Interval interval, int threadNumber) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        long intervalToOneThread = countOperation(interval, threadNumber);
        int beginPoint = interval.getBegine();

        long begineTime = System.currentTimeMillis();
        Thread thread[] = new Thread[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            int begin = beginPoint + 1;
            int end = (int) Math.round(Math.sqrt(Math.pow(begin, 2) + 4 * intervalToOneThread));
            beginPoint = end;
            Interval localInterval = new Interval();
            localInterval.setBegine(begin);
            localInterval.setEnd(end);
            thread[i] = new Thread(new Searcher(localInterval, list));
            thread[i].start();
        }
        for (int i = 0; i < threadNumber; i++) {
            thread[i].join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Simple final time = " + (endTime - begineTime));

        return list;
    }

}
