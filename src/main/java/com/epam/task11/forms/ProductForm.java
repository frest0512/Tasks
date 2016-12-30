package com.epam.task11.forms;

import com.epam.task11.entity.ProductSearchCriteria;

import java.util.Arrays;

public class ProductForm {
    private String sortingType;
    private String itemsNumberOnPage;
    private String pageNumber;
    private String priceFrom;
    private String priceTo;
    private String productName;
    private String[] manufacturer;
    private String[] categories;

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public String getItemsNumberOnPage() {
        return itemsNumberOnPage;
    }

    public void setItemsNumberOnPage(String itemsNumberOnPage) {
        this.itemsNumberOnPage = itemsNumberOnPage;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String[] getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String[] manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public ProductSearchCriteria createProductSearchCriteria() {
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria();
        productSearchCriteria.setItemsNumberOnPage(getItemsNumberOnPage());
        productSearchCriteria.setSortingType(getSortingType());
        productSearchCriteria.setPageNumber(getPageNumber());
        ProductSearchCriteria.SearchForm searchForm = productSearchCriteria.new SearchForm();
        if (getCategories() != null) {
            searchForm.setCategories(Arrays.asList(getCategories()));
        }
        if (getManufacturer() != null) {
            searchForm.setManufacturer(Arrays.asList(getManufacturer()));
        }
        searchForm.setPriceFrom(getPriceFrom());
        searchForm.setPriceTo(getPriceTo());
        searchForm.setProductName(getProductName());
        productSearchCriteria.setSearchForm(searchForm);
        return productSearchCriteria;
    }

    @Override
    public String toString() {
        return "ProductForm{" +
                "sortingType='" + sortingType + '\'' +
                ", itemsNumberOnPage='" + itemsNumberOnPage + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", priceFrom='" + priceFrom + '\'' +
                ", priceTo='" + priceTo + '\'' +
                ", productName='" + productName + '\'' +
                ", manufacturer=" + Arrays.toString(manufacturer) +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }
}
