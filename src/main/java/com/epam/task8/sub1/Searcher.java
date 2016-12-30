package com.epam.task8.sub1;

import com.epam.task8.entity.Interval;

import java.util.ArrayList;
import java.util.List;

import static com.epam.task8.util.Checkers.findPrime;

public class Searcher implements Runnable {
    private Interval interval;
    private List<Integer> globalList;

    public Searcher(Interval interval, List<Integer> store) {
        this.interval = interval;
        this.globalList = store;
    }

    @Override
    public void run() {

        ArrayList<Integer> list = new ArrayList<>();
        findPrime(interval, list);
        synchronized (globalList) {
            globalList.addAll(list);
        }
    }
}
