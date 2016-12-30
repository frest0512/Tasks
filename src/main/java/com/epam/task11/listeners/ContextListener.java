package com.epam.task11.listeners;

import com.epam.task11.captcha.CaptchaCleaner;
import com.epam.task11.captcha.CaptchaImageGenerator;
import com.epam.task11.captcha.CaptchaTextGenerator;
import com.epam.task11.captcha.handlers.CookieCaptchaHandler;
import com.epam.task11.captcha.handlers.HiddenFieldCaptchaHandler;
import com.epam.task11.captcha.handlers.SessionCaptchaHandler;
import com.epam.task11.dao.api.*;
import com.epam.task11.dao.impl.mysql.*;
import com.epam.task11.db.TransactionManager;
import com.epam.task11.entity.Captcha;
import com.epam.task11.exeptions.AppException;
import com.epam.task11.exeptions.IllegalLanguageStorageType;
import com.epam.task11.exeptions.WrongCaptchaTypeException;
import com.epam.task11.language.CookieLanguageDescriptor;
import com.epam.task11.language.SessionLanguageDescriptor;
import com.epam.task11.services.*;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(ContextListener.class);
    private ScheduledExecutorService service;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.debug("ContextListener init started");
        ServletContext servletContext = servletContextEvent.getServletContext();
        setServices(servletContext);
        try {
            chooseAndSetCaptchaHandler(servletContext);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException");
            throw new AppException(e);
        }
        setCaptchaImageGenerator(servletContext);
        File file = new File(servletContext.getInitParameter(Constants.AVATARS_STORAGE));
        if (!file.exists()) {
            LOG.debug("Avatars folder does not exist, creating folder path = " + file.getPath());
            if (!file.mkdirs()) {
                LOG.error("Can not create avatars folder");
                throw new AppException("Can not create avatars folder");
            } else {
                LOG.debug("Avatar folder was successfully created!");
            }
        }
        setLocaleStorageType(servletContext);
        LOG.debug("ContextListener init finished");
    }

    private void setLocaleStorageType(ServletContext servletContext) {
        String lang = servletContext.getInitParameter(Constants.WEB_XML_LANGUAGE_STORAGE_TYPE);
        switch (lang) {
            case Constants.LANGUAGE_STORAGE_TYPE_SESSION: {
                servletContext.setAttribute(Constants.LANGUAGE_DESCRIPTOR_NAME, new SessionLanguageDescriptor());
                break;
            }
            case Constants.LANGUAGE_STORAGE_TYPE_COOKIE: {
                int cookieLiveTime = Integer.parseInt(servletContext.getInitParameter(Constants.TIME_LANGUAGE_COOKIE_LIVE));
                servletContext.setAttribute(Constants.LANGUAGE_DESCRIPTOR_NAME, new CookieLanguageDescriptor(cookieLiveTime));
                break;
            }
            default: {
                throw new IllegalLanguageStorageType(lang + " is not a type to store language");
            }
        }
    }

    private void setCaptchaImageGenerator(ServletContext servletContext) {
        int imageHeight = 27;
        int imageWidth = 127;
        CaptchaImageGenerator captchaImageGenerator = new CaptchaImageGenerator(imageHeight, imageWidth);
        servletContext.setAttribute(Constants.CAPTCHA_IMAGE_GENERATION, captchaImageGenerator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.debug("ContextListener destroyed started");
        if (service != null) {
            service.shutdown();
        }
        LOG.debug("ContextListener destroyed finished");
    }

    public void setServices(ServletContext servletContext) {
        TransactionManager transactionManager = null;
        try {
            transactionManager = new TransactionManager();
        } catch (NamingException e) {
            LOG.error(e.getClass().getSimpleName());
            throw new AppException(e);
        }
        UserDAO userDAO = new UserDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        DeliveryDAO deliveryDAO = new DeliveryDAOImpl();
        PaymentDAO paymentDAO = new PaymentDAOImpl();

        UserService userService = new UserService(userDAO, transactionManager);
        CategoryService categoryService = new CategoryService(categoryDAO, transactionManager);
        ProductService productService = new ProductService(productDAO, transactionManager);
        ManufacturerService manufacturerService = new ManufacturerService(manufacturerDAO, transactionManager);
        OrderService orderService = new OrderService(orderDAO, deliveryDAO, paymentDAO, orderItemDAO, transactionManager);

        servletContext.setAttribute(Constants.CATEGORY_SERVICE_NAME, categoryService);
        servletContext.setAttribute(Constants.USER_SERVICE_NAME, userService);
        servletContext.setAttribute(Constants.PRODUCT_SERVICE_NAME, productService);
        servletContext.setAttribute(Constants.MANUFACTURER_SERVICE_NAME, manufacturerService);
        servletContext.setAttribute(Constants.ORDER_SERVICE_NAME, orderService);
    }

    public void chooseAndSetCaptchaHandler(ServletContext servletContext) throws NoSuchAlgorithmException {
        LOG.debug("Init captcha started");
        int secondsBetweenChecks = Integer.parseInt(servletContext.getInitParameter("timeCaptchaCheckRepeats"));
        LOG.debug("secondsBetweenChecks = " + secondsBetweenChecks);
        String myContextParam = servletContext.getInitParameter("CaptchaStorage");
        LOG.debug("myContextParam = " + myContextParam);
        long timeLimit = Long.parseLong(servletContext.getInitParameter("timeCaptchaIsValid"));
        LOG.debug("timeLimit = " + timeLimit);
        String algorithm = servletContext.getInitParameter("captchaGenerationAlgorithm");
        LOG.debug("Captcha random algorithm = " + algorithm);
        int symbolsNumber = Integer.parseInt(servletContext.getInitParameter("captchaGenerationSymbolsNumber"));
        LOG.debug("Captcha length = " + symbolsNumber);
        CaptchaTextGenerator captchaTextGenerator = new CaptchaTextGenerator(algorithm, symbolsNumber);
        switch (myContextParam) {
            case "hidden": {
                LOG.debug("CaptchaHandler is = HiddenFieldCaptchaHandler");
                Map<String, Captcha> map = new ConcurrentHashMap<>();
                HiddenFieldCaptchaHandler hiddenFieldCaptchaHandler = new HiddenFieldCaptchaHandler(map, timeLimit, captchaTextGenerator);
                servletContext.setAttribute(Constants.CAPTCHA_HANDLER_NAME, hiddenFieldCaptchaHandler);
                service = Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(new CaptchaCleaner(hiddenFieldCaptchaHandler), 0, secondsBetweenChecks, TimeUnit.SECONDS);
                break;
            }
            case "cookie": {
                LOG.debug("CaptchaHandler is = CookieCaptchaHandler");
                Map<String, Captcha> map = new ConcurrentHashMap<>();
                CookieCaptchaHandler cookieCaptchaHandler = new CookieCaptchaHandler(map, timeLimit, captchaTextGenerator);
                servletContext.setAttribute(Constants.CAPTCHA_HANDLER_NAME, cookieCaptchaHandler);
                service = Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(new CaptchaCleaner(cookieCaptchaHandler), 0, secondsBetweenChecks, TimeUnit.SECONDS);
                break;
            }
            case "session": {
                LOG.debug("CaptchaHandler is = SessionCaptchaHandler");
                servletContext.setAttribute(Constants.CAPTCHA_HANDLER_NAME, new SessionCaptchaHandler(timeLimit, captchaTextGenerator));
                break;
            }
            default: {
                LOG.debug("CaptchaHandler is = default");
                throw new WrongCaptchaTypeException();
            }
        }
        LOG.debug("Init captcha finished");

    }
}
