package com.epam.task1.entity;

import com.epam.task7.anotations.FieldToRequest;

public class Helicopter extends AirTransport {

    @FieldToRequest(key = "MAX_FLY_HIGH")
    private int maxFlyHigh;

    public Helicopter(double weight, int price, double maxSpeed, double capacity, int maxFlyHigh) {
        super(weight, price, maxSpeed, capacity);
        this.maxFlyHigh = maxFlyHigh;
    }

    public Helicopter() {
    }

    @Override
    public String toString() {
        return super.toString() + " Helicopter{" +
                "maxFlyHigh=" + maxFlyHigh +
                '}';
    }

    public int getMaxFlyHigh() {
        return maxFlyHigh;
    }

    public void setMaxFlyHigh(int maxFlyHigh) {
        this.maxFlyHigh = maxFlyHigh;
    }
}
