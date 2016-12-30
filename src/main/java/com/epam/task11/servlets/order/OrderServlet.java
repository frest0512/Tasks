package com.epam.task11.servlets.order;


import com.epam.task11.entity.db.Delivery;
import com.epam.task11.entity.db.Order;
import com.epam.task11.entity.db.Payment;
import com.epam.task11.entity.db.User;
import com.epam.task11.entity.order.OrderedProduct;
import com.epam.task11.entity.order.ShoppingCart;
import com.epam.task11.services.OrderService;
import com.epam.task11.util.Constants;
import com.epam.task11.util.Path;
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
import java.util.stream.Collectors;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(OrderServlet.class);
    private OrderService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (OrderService) config.getServletContext().getAttribute(Constants.ORDER_SERVICE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_CART);
        User user = (User) req.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_USER);
        List<OrderedProduct> list = cart
                .getItems()
                .entrySet()
                .stream()
                .map(entry -> new OrderedProduct(entry.getKey(), entry.getValue(), entry.getKey().getPrice()))
                .collect(Collectors.toList());
        Order order = new Order();
        order.setProducts(list);
        order.setUser(user);
        order.setTotal(cart.totalPrice());
        req.getSession().setAttribute(Constants.ATTRIBUTES_APPENDER + Constants.ORDER_CART, order);
        req.getRequestDispatcher(Path.ORDER_PAGE_SOURCE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("OrderServlet started");
        LOG.debug("Getting params");
        Order order = (Order) req.getSession().getAttribute(Constants.ATTRIBUTES_APPENDER + Constants.ORDER_CART);
        LOG.debug("Got params");
        LOG.debug("Creating entity");
        if (order == null) {
            throw new IllegalArgumentException("Order attribute is null");
        }
        order.setDelivery(createDeliveryFromRequest(req));
        order.setPayment(createPaymentFromRequest(req));
        LOG.debug("Created entity");
        LOG.debug("Making order");
        boolean res = service.makeOrder(order);
        LOG.debug("Making order result is = " + res);
        if (res) {
            req.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + Constants.SESSION_CART);
            req.getSession().removeAttribute(Constants.ATTRIBUTES_APPENDER + Constants.ORDER_CART);
            req.setAttribute(Constants.ATTRIBUTES_APPENDER + "_info_order", order);
            req.getRequestDispatcher(Path.SUCCESS_ORDER_PAGE).forward(req, resp);
        } else {
            req.getRequestDispatcher(Path.FAIL_ORDER_PAGE).forward(req, resp);
        }
    }

    private Delivery createDeliveryFromRequest(HttpServletRequest req) {
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String building = req.getParameter("building");
        Delivery delivery = new Delivery();
        delivery.setBuilding(building);
        delivery.setCity(city);
        delivery.setStreet(street);
        return delivery;
    }

    private Payment createPaymentFromRequest(HttpServletRequest req) {
        String cartNumber = req.getParameter("cartNumber");
        String cartDateExpire = req.getParameter("cartDateExpire");
        Payment payment = new Payment();
        payment.setCartNumber(cartNumber);
        payment.setDateCardExpire(cartDateExpire);
        return payment;
    }
}