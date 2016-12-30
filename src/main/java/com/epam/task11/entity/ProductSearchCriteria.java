package com.epam.task11.entity;

import java.util.List;

public class ProductSearchCriteria {
    private String sortingType;
    private String itemsNumberOnPage;
    private String pageNumber;
    private SearchForm searchForm;

    public SearchForm getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

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

    @Override
    public String toString() {
        return "ProductSearchCriteria{" +
                "sortingType='" + sortingType + '\'' +
                ", itemsNumberOnPage='" + itemsNumberOnPage + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", searchForm=" + searchForm +
                '}';
    }

    public class SearchForm {
        private String priceFrom;
        private String priceTo;
        private String productName;
        private List<String> manufacturer;
        private List<String> categories;

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

        public List<String> getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(List<String> manufacturer) {
            this.manufacturer = manufacturer;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        @Override
        public String toString() {
            return "SearchForm{" +
                    "priceFrom='" + priceFrom + '\'' +
                    ", priceTo='" + priceTo + '\'' +
                    ", productName='" + productName + '\'' +
                    ", manufacturer=" + manufacturer +
                    ", categories=" + categories +
                    '}';
        }
    }

}
