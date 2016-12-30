package com.epam.task11.servlets.util;

import com.epam.task11.entity.order.ShoppingCart;
import com.epam.task11.util.Constants;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class Cart {
    public static ShoppingCart getOrCreateEmptyCart(HttpServletRequest request) {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_CART);
        if (cart == null) {
            cart = new ShoppingCart();
            request.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_CART, cart);
        }
        return cart;
    }

    public static JSONObject prepareStandardResponse(ShoppingCart cart) {
        JSONObject response = new JSONObject();
        if(cart!=null) {
            response.put("totalPrice", cart.totalPrice());
            response.put("totalItems", cart.size());
        }else {
            response.put("totalPrice", 0);
            response.put("totalItems", 0);
        }
        return response;
    }
}
