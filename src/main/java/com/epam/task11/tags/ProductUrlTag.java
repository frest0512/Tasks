package com.epam.task11.tags;

import com.epam.task11.entity.ProductSearchCriteria;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ProductUrlTag extends SimpleTagSupport {
    private static final Logger LOG = LogManager.getLogger(ProductUrlTag.class);

    private String page;
    private String sortBy;
    private String itemsOnPage;
    private ProductSearchCriteria.SearchForm param;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getItemsOnPage() {
        return itemsOnPage;
    }

    public void setItemsOnPage(String itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    public ProductSearchCriteria.SearchForm getParam() {
        return param;
    }

    public void setParam(ProductSearchCriteria.SearchForm param) {
        this.param = param;
    }

    @Override
    public void doTag() throws JspException, IOException {
        LOG.debug("ProductUrlTag started");
        StringBuilder resUrl = new StringBuilder();
        if (param != null) {
            if (param.getPriceFrom() != null) {
                resUrl.append(Constants.PRICE_FROM).append("=").append(param.getPriceFrom()).append("&");
            }
            if (param.getPriceTo() != null) {
                resUrl.append(Constants.PRICE_TO).append("=").append(param.getPriceTo()).append("&");
            }
            if (param.getProductName() != null) {
                resUrl.append(Constants.PRODUCT_NAME).append("=").append(param.getProductName()).append("&");
            }
            if (param.getCategories() != null) {
                for (String category : param.getCategories()) {
                    resUrl.append(Constants.CATEGORIES).append("=").append(category).append("&");
                }
            }
            if (param.getManufacturer() != null) {
                for (String manufacturer : param.getManufacturer()) {
                    resUrl.append(Constants.MANUFACTURER).append("=").append(manufacturer).append("&");
                }
            }
        }
        if (sortBy.length() != 0) {
            resUrl.append(Constants.SORT_TYPE).append("=").append(sortBy).append("&");
        }
        if (itemsOnPage.length() != 0) {
            resUrl.append(Constants.ITEMS_ON_PAGE).append("=").append(itemsOnPage).append("&");
        }
        if (page.length() != 0) {
            resUrl.append(Constants.PAGE_NUMBER).append("=").append(page).append("&");
        }
        resUrl.deleteCharAt(resUrl.length() - 1);
        getJspContext().getOut().write(resUrl.toString());
        LOG.debug("URL = " + resUrl.toString());
        LOG.debug("ProductUrlTag finished");
    }
/*    @Override
    public void doTag() throws JspException, IOException {
        LOG.debug("ProductUrlTag started");
        StringBuilder resUrl = new StringBuilder();
        Map<String,String> paramsMap = convertToMap(param);
        if(sortBy.length()!=0){
            paramsMap.put(Constants.SORT_TYPE,sortBy);
        }
        if(itemsOnPage.length()!=0){
            paramsMap.put(Constants.ITEMS_ON_PAGE,itemsOnPage);
        }
        if(page.length()!=0){
            if(paramsMap.containsKey(Constants.PAGE_NUMBER)){
                paramsMap.replace(Constants.PAGE_NUMBER,page);
            }else{
                paramsMap.put(Constants.PAGE_NUMBER,page);
            }
        }
        paramsMap.entrySet().stream().forEach(entry -> resUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        resUrl.deleteCharAt(resUrl.length()-1);
        LOG.debug("Url = "+resUrl.toString());
        getJspContext().getOut().write(resUrl.toString());
        LOG.debug("ProductUrlTag finished");
    }
    private Map<String,String> convertToMap(String params){
        String[] keyvals = params.substring(1,params.length()-1).split(", ");
        Map<String,String> res = new HashMap<>();
        for (String s:keyvals) {
            String[] keyval = s.split("=");
            if(keyval.length==2){
                res.put(keyval[0],keyval[1]);
            }
        }
        return res;
    }*/
}
