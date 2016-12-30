package com.epam.task11.util;

import com.epam.task11.forms.ProductForm;

public class DefaultFiller {
    public void fillProductForm(ProductForm criteria) {
        if (criteria.getSortingType() == null || criteria.getSortingType().length() == 0) {
            criteria.setSortingType(Constants.DEFAULT_PAGE_SORTING_FIELD);
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber().length() == 0) {
            criteria.setPageNumber(Constants.DEFAULT_PAGE_NUMBER);
        }
        if (criteria.getItemsNumberOnPage() == null || criteria.getItemsNumberOnPage().length() == 0) {
            criteria.setItemsNumberOnPage(Constants.DEFAULT_ITEMS_ON_PAGE);
        }

        if (criteria.getPriceFrom() == null || criteria.getPriceFrom().length() == 0) {
            criteria.setPriceFrom(null);
        }
        if (criteria.getPriceTo() == null || criteria.getPriceTo().length() == 0) {
            criteria.setPriceTo(null);
        }
        if (criteria.getProductName() == null || criteria.getProductName().length() == 0) {
            criteria.setProductName(null);
        }
        if (criteria.getManufacturer() == null) {
            criteria.setManufacturer(null);
        }
        if (criteria.getCategories() == null) {
            criteria.setCategories(null);
        }
    }
}
