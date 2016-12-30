package com.epam.task8.sub3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final Object monitor = new Object();
        Scanner scanner = new Scanner(System.in);

        SearchThread runnable = new SearchThread(monitor);
        Thread threadMain = new Thread(runnable);

        MainThread mainThread = new MainThread(monitor, scanner, runnable);
        Thread threadSearcher = new Thread(mainThread);
        threadSearcher.start();
        threadMain.start();
    }
}
