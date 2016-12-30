package com.epam.task11.servlets;

import com.epam.task11.entity.ProductSearchCriteria;
import com.epam.task11.entity.db.Category;
import com.epam.task11.entity.db.Manufacturer;
import com.epam.task11.entity.db.Product;
import com.epam.task11.forms.ProductForm;
import com.epam.task11.forms.creators.FormCreator;
import com.epam.task11.services.CategoryService;
import com.epam.task11.services.ManufacturerService;
import com.epam.task11.services.ProductService;
import com.epam.task11.util.Constants;
import com.epam.task11.util.DefaultFiller;
import com.epam.task11.util.Path;
import com.epam.task11.validators.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ProductsServlet.class);
    private ProductService productService;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;
    private DefaultFiller defaultFiller = new DefaultFiller();

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(Constants.PRODUCT_SERVICE_NAME);
        manufacturerService = (ManufacturerService) config.getServletContext().getAttribute(Constants.MANUFACTURER_SERVICE_NAME);
        categoryService = (CategoryService) config.getServletContext().getAttribute(Constants.CATEGORY_SERVICE_NAME);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("ProductsServlet started");
        LOG.debug("Creating product form");
        ProductForm productForm = FormCreator.createProductForm(req);
        LOG.debug("Validating product form");
        Map<String, String> validationResult = FormValidator.validateProductForm(productForm);
        if (validationResult.size() > 0) {
            LOG.warn("Some params is not correct");
            validationResult.entrySet().stream().forEach(entry -> {
                LOG.warn(entry.getKey() + ": " + entry.getValue());
            });
        }
        defaultFiller.fillProductForm(productForm);

        ProductSearchCriteria productSearchCriteria = productForm.createProductSearchCriteria();

        List<Product> productsCond = productService.getProducts(productSearchCriteria);
        int itemsWithThisCond = productService.getProductCount(productSearchCriteria.getSearchForm());
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        List<Category> categories = categoryService.getAllCategory();

        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.REQUEST_MANUFACTURERS, manufacturers);
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.REQUEST_CATEGORIES, categories);
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.REQUEST_PRODUCTS, productsCond);

        int numberOfPages = (int) Math.ceil((double) itemsWithThisCond / Integer.parseInt(productSearchCriteria.getItemsNumberOnPage()));

        int pagenumber = Integer.parseInt(productSearchCriteria.getPageNumber());
        int itemsOnPage = Integer.parseInt(productSearchCriteria.getItemsNumberOnPage());
        int end = pagenumber * itemsOnPage;
        int from = (pagenumber - 1) * itemsOnPage + 1;
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.PRODUCTS_NUMBER_PAGES, numberOfPages);
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.PRODUCT_FORM, productSearchCriteria);
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.PRODUCTS_COUNT, itemsWithThisCond);
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.PRODUCTS_RANGE_FROM, from);
        req.setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.PRODUCTS_RANGE_TO, end);
        LOG.debug("All is ok");
        req.getRequestDispatcher(Path.PRODUCTS_PAGE_SOURCE).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
