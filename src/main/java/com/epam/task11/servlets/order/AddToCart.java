package com.epam.task11.servlets.order;

import com.epam.task11.entity.db.Product;
import com.epam.task11.entity.order.ShoppingCart;
import com.epam.task11.services.ProductService;
import com.epam.task11.servlets.util.Cart;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(AddToCart.class);
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(Constants.PRODUCT_SERVICE_NAME);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("AddToCart started");
        LOG.debug("Getting params");
        String productId = request.getParameter(Constants.REQUEST_PRODUCT_ID);
        ShoppingCart cart = Cart.getOrCreateEmptyCart(request);
        LOG.debug("Got params params");
        LOG.debug("Parsing incoming id to Integer");
        int id = -1;
        if (productId != null) {
            id = Integer.parseInt(productId);
        }
        LOG.debug("Incoming id = " + id);
        Product product = productService.getProductById(id);
        LOG.debug("Got product by this id = " + product);
        LOG.debug("Adding item to order");
        cart.addItem(product);
        LOG.debug("Item added");
        PrintWriter out = response.getWriter();
        LOG.debug("Creating JSON object");
        out.println(Cart.prepareStandardResponse(cart));
        LOG.debug("JSON saved");
        out.close();
        LOG.debug("Cart = " + cart);
    }
}
