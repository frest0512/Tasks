package com.epam.task11.db;

import com.epam.task11.entity.ProductSearchCriteria;
import com.epam.task11.util.QueryBuilder;

import java.util.List;

public class QueryProvider {
    private QueryBuilder builder = new QueryBuilder();

    public QueryProvider() {

    }

    public List<Object> params() {
        return builder.params();
    }

    public String build(ProductSearchCriteria.SearchForm searchForm) {
        buildFromSearchForm(searchForm);
        return builder.fetch();
    }

    public String build(ProductSearchCriteria productSearchCriteria) {
        buildFromSearchForm(productSearchCriteria.getSearchForm());
        builder.order(productSearchCriteria.getSortingType());
        int itemsOnPage = Integer.parseInt(productSearchCriteria.getItemsNumberOnPage());
        int pageNumber = Integer.parseInt(productSearchCriteria.getPageNumber());
        int end = itemsOnPage * pageNumber;
        int begine = itemsOnPage * (pageNumber - 1);
        builder.limit(begine, end);
        return builder.fetch();
    }

    private void buildFromSearchForm(ProductSearchCriteria.SearchForm searchForm) {
        builder.like("name", builder.nameInTheMiddle(searchForm.getProductName()));
        builder.between("price", searchForm.getPriceFrom(), searchForm.getPriceTo());
        builder.in("idmanufacturer", searchForm.getManufacturer());
        builder.in("categoryid", searchForm.getCategories());
    }

}
