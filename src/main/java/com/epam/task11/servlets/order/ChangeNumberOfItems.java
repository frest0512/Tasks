package com.epam.task11.servlets.order;

import com.epam.task11.entity.db.Product;
import com.epam.task11.entity.order.ShoppingCart;
import com.epam.task11.servlets.util.Cart;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/changeNumberOfItems")
public class ChangeNumberOfItems extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ChangeNumberOfItems.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("ChangeNumberOfItems started");
        PrintWriter writer = resp.getWriter();
        String productId = request.getParameter(Constants.REQUEST_PRODUCT_ID);
        String numberOfItems = request.getParameter(Constants.REQUEST_CART_NEW_NUMBER_OF_ITEMS);
        int id = Integer.parseInt(productId);
        int number = Integer.parseInt(numberOfItems);
        ShoppingCart cart = Cart.getOrCreateEmptyCart(request);
        cart.getItems().entrySet().stream().forEach(e -> {
            if (e.getKey().getId() == id) {
                e.setValue(number);
            }
        });
        JSONObject object = Cart.prepareStandardResponse(cart);
        cart.getItems().entrySet().stream().forEach(e -> {
            if (e.getKey().getId() == id) {
                object.put("newItemTotalPrice",e.getKey().getPrice()*e.getValue());
            }
        });
        writer.println(object);
    }
}
