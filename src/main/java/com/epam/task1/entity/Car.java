package com.epam.task1.entity;

import com.epam.task7.anotations.FieldToRequest;

public class Car extends LandTransport {
    @FieldToRequest(key = "DOORS_NUMBER")
    private int doorsNumber;

    public Car() {
    }

    public Car(double weight, int price, double maxSpeed, int wheelNumber, int horsePower, int doorsNumber) {
        super(weight, price, maxSpeed, wheelNumber, horsePower);
        this.doorsNumber = doorsNumber;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + doorsNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (doorsNumber != other.doorsNumber)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " Car [doorsNumber=" + doorsNumber + "]";
    }
}
