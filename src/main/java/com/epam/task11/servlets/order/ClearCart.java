package com.epam.task11.servlets.order;

import com.epam.task11.entity.order.ShoppingCart;
import com.epam.task11.servlets.util.Cart;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/clearCart")
public class ClearCart extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ClearCart.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("ClearCart started");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_CART);
        LOG.debug("Clear cart = " + cart);
        if (cart != null) {
            cart.clear();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.println(Cart.prepareStandardResponse(cart));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
