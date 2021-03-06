package com.epam.task7.sub2.transport.impl;

import com.epam.task7.anotations.FieldToRequest;
import com.epam.task7.sub2.transport.api.Transport;

public class DefaultTransport implements Transport {
    @FieldToRequest(key = "WEIGHT")
    private double weight;
    @FieldToRequest(key = "PRICE")
    private int price;
    @FieldToRequest(key = "MAX_SPEED")
    private double maxSpeed;

    public DefaultTransport() {
    }

    public DefaultTransport(double weight, int price, double maxSpeed) {
        this.weight = weight;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(maxSpeed);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultTransport other = (DefaultTransport) obj;
        if (Double.doubleToLongBits(maxSpeed) != Double.doubleToLongBits(other.maxSpeed))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DefaultTransport [weight=" + weight + ", price=" + price + ", maxSpeed=" + maxSpeed + "]";
    }
}
