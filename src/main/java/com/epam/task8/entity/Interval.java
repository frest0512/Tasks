package com.epam.task8.entity;

public class Interval {
    private int begine;
    private int end;

    public int getBegine() {
        return begine;
    }

    public void setBegine(int begine) {
        this.begine = begine;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "begine=" + begine +
                ", end=" + end +
                '}';
    }
}
