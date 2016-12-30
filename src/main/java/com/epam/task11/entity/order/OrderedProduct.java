package com.epam.task11.entity.order;

import com.epam.task11.entity.db.Product;

public final class OrderedProduct {
    private Product product;
    private int amount;
    private int orderedPrice;

    public OrderedProduct(Product product, int amount, int orderedPrice) {
        this.product = product;
        this.amount = amount;
        this.orderedPrice = orderedPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getOrderedPrice() {
        return orderedPrice;
    }
}
