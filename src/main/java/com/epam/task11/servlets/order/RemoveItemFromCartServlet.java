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

@WebServlet("/removeItemFromCart")
public class RemoveItemFromCartServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(RemoveItemFromCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("removeItemFromCart started");
        LOG.debug("getting params");
        String productId = request.getParameter(Constants.REQUEST_PRODUCT_ID);
        int id = Integer.parseInt(productId);
        LOG.debug("product id = " + productId);
        ShoppingCart cart = Cart.getOrCreateEmptyCart(request);
        Product product = cart.getItems()
                .entrySet()
                .stream()
                .map(productIntegerEntry -> productIntegerEntry.getKey())
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
        Integer res = cart.getItems().remove(product);
        JSONObject myObj = Cart.prepareStandardResponse(cart);
        if (res != null) {
            myObj.put("success", true);
        } else {
            myObj.put("success", false);
        }

        PrintWriter writer = resp.getWriter();
        writer.println(myObj);
    }


}
