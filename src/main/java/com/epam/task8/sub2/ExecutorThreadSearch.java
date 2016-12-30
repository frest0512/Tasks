package com.epam.task8.sub2;

import com.epam.task8.entity.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.epam.task8.util.Checkers.countOperation;

public class ExecutorThreadSearch {
    public List<Integer> search(Interval interval, int threadNumber) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool(threadNumber);
        List<Integer> list = new ArrayList<>();
        long intervalToOneThread = countOperation(interval, threadNumber);
        int beginPoint = interval.getBegine();

        long begineTime = System.currentTimeMillis();
        Future<List<Integer>>[] futures = new Future[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            int begin = beginPoint + 1;
            int end = (int) Math.round(Math.sqrt(Math.pow(begin, 2) + 4 * intervalToOneThread));
            beginPoint = end;
            Interval localInterval = new Interval();
            localInterval.setBegine(begin);
            localInterval.setEnd(end);
            futures[i] = executor.submit(new CallableSearcher(localInterval));
        }
        for (int i = 0; i < threadNumber; i++) {
            try {
                list.addAll(futures[i].get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Executor final time = " + (endTime - begineTime));

        return list;
    }
}
