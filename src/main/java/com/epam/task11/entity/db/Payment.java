package com.epam.task11.entity.db;

public class Payment {
    private long id;
    private String cartNumber;
    private String dateCardExpire;

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String getDateCardExpire() {
        return dateCardExpire;
    }

    public void setDateCardExpire(String dateCardExpire) {
        this.dateCardExpire = dateCardExpire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", cartNumber='" + cartNumber + '\'' +
                ", dateCardExpire='" + dateCardExpire + '\'' +
                '}';
    }
}
