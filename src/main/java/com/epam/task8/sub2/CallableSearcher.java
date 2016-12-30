package com.epam.task8.sub2;

import com.epam.task8.entity.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.epam.task8.util.Checkers.findPrime;

public class CallableSearcher implements Callable<List<Integer>> {

    private Interval interval;

    @Override
    public List<Integer> call() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        findPrime(interval, list);
        return list;
    }

    public CallableSearcher(Interval interval) {
        this.interval = interval;
    }
}
