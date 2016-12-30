package com.epam.task8.sub3;

import java.util.Scanner;

public class MainThread implements Runnable {

    private Object monitor;
    private Scanner scanner;
    private SearchThread searcher;


    public MainThread(Object monitor, Scanner scanner, SearchThread searcher) {
        this.monitor = monitor;
        this.scanner = scanner;
        this.searcher = searcher;

    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Print file name on 'Exit' to exit");
            String fileName = null;
            if (scanner.hasNext()) {
                fileName = scanner.next();
            }
            if (fileName.equals("Exit")) {
                return;
            }
            searcher.setFileName(fileName);
            synchronized (monitor) {
                monitor.notifyAll();
            }
            while (!searcher.isFinished()) {
                System.out.println(searcher.getCurLength());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End");
        }

    }
}
