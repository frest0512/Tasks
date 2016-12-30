package com.epam.task8.sub3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SearchThread implements Runnable {
    private volatile boolean isFinished = false;
    private String fileName;
    private int curLength;
    private int indexB;
    private int indexA;
    private Object monitor;

    public SearchThread(Object monitor) {
        this.monitor = monitor;
    }

    public void setCurLength(int curLength) {
        this.curLength = curLength;
    }

    public int getIndexB() {
        return indexB;
    }

    public int getIndexA() {
        return indexA;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getCurLength() {
        return curLength;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                try {
                    System.out.println("Wait in search thread");
                    monitor.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //bytes = new byte[]{1, 2, 3, 5, 6, 5, 4, 2, 3, 5, 6, 1, 2, 3};
            byte[] currentPattern;
            byte[] maxPattern = new byte[0];

            for (int i = 0; i < bytes.length; i++) {
                for (int j = i + 1; j < bytes.length; j++) {
                    currentPattern = Arrays.copyOfRange(bytes, i, j);
                    int temp;
                    if ((temp = indexOfPattern(currentPattern, bytes, i + 1)) != -1) {
                        if (currentPattern.length > maxPattern.length) {
                            indexB = temp;
                            indexA = i;
                            maxPattern = currentPattern;
                            curLength = currentPattern.length;
                        }
                    }
                }
            }
            isFinished = true;
            System.out.println(Arrays.toString(maxPattern));
            System.out.println("Notify in search thread");
        }

    }


    private int indexOfPattern(byte[] pattern, byte[] array, int start) {

        for (int i = start; i < array.length; i++) {
            if (Arrays.equals(Arrays.copyOfRange(array, i, i + pattern.length), pattern)) {
                return i;
            }
        }

        return -1;
    }

}
