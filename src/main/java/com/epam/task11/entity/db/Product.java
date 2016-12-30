package com.epam.task11.entity.db;

import com.epam.task11.annotations.DBField;
import com.epam.task11.util.Constants;

import java.io.Serializable;

public class Product implements Serializable {


    private Manufacturer manufacturer;
    @DBField(DBFieldName = Constants.DB_PRODUCT_ID)
    private int id;
    @DBField(DBFieldName = Constants.DB_PRODUCT_NAME)
    private String name;
    @DBField(DBFieldName = Constants.DB_PRODUCT_PRICE)
    private int price;
    @DBField(DBFieldName = Constants.DB_PRODUCT_DESCRIPTION)
    private String description;
    @DBField(DBFieldName = Constants.DB_PRODUCT_IMAGE_FIRST)
    private String imageFirst;
    @DBField(DBFieldName = Constants.DB_PRODUCT_IMAGE_SECOND)
    private String imageSecond;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFirst() {
        return imageFirst;
    }

    public void setImageFirst(String imageFirst) {
        this.imageFirst = imageFirst;
    }

    public String getImageSecond() {
        return imageSecond;
    }

    public void setImageSecond(String imageSecond) {
        this.imageSecond = imageSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (manufacturer != null ? !manufacturer.equals(product.manufacturer) : product.manufacturer != null)
            return false;
        return description != null ? description.equals(product.description) : product.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "manufacturer=" + manufacturer +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageFirst='" + imageFirst + '\'' +
                '}';
    }
}
