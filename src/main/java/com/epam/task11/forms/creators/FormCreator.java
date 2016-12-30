package com.epam.task11.forms.creators;

import com.epam.task11.forms.LoginForm;
import com.epam.task11.forms.ProductForm;
import com.epam.task11.forms.RegisterForm;
import com.epam.task11.servlets.entrance.LoginServlet;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FormCreator {
    private static final Logger LOG = LogManager.getLogger(LoginServlet.class);

    public static RegisterForm createRegisterForm(HttpServletRequest request) throws IOException, ServletException {
        LOG.debug("Getting params");
        String firstName = request.getParameter(Constants.DB_FIRST_NAME);
        String lastName = request.getParameter(Constants.DB_LAST_NAME);
        String userName = request.getParameter(Constants.DB_USER_NAME);
        String email = request.getParameter(Constants.DB_EMAIL);
        String password = request.getParameter(Constants.DB_PASSWORD);
        String isSubscribe = request.getParameter(Constants.DB_SUBSCRIBE_NAME);
        String captcha = request.getParameter(Constants.USER_CAPTCHA_CODE);
        Part file = request.getPart(Constants.DB_AVATAR_NAME);
        String imageName = UUID.randomUUID().toString();
        LOG.debug("Got params, image name = " + imageName);
        InputStream inputStream = file.getInputStream();
        Image image = ImageIO.read(inputStream);
        LOG.debug(image);
        String imgPath = request.getServletContext().getInitParameter(Constants.AVATARS_STORAGE);
        ImageIO.write((RenderedImage) image, "jpg", new File(imgPath + imageName + "." + Constants.IMG_FORMAT));
        LOG.debug("Saving params into user");
        RegisterForm registerForm = new RegisterForm();
        registerForm.setFirstName(firstName);
        registerForm.setLastName(lastName);
        registerForm.setUserName(userName);
        registerForm.setEmail(email);
        registerForm.setPassword(password);
        registerForm.setIsSubscribe(isSubscribe);
        registerForm.setAvatarName(imageName);
        registerForm.setCaptcha(captcha);
        LOG.debug("Params saved");
        return registerForm;
    }

    public static LoginForm createLoginForm(HttpServletRequest req) {
        LoginForm loginForm = new LoginForm();
        String userName = req.getParameter(Constants.DB_USER_NAME);
        String password = req.getParameter(Constants.DB_PASSWORD);
        LOG.debug("Got params");
        LOG.debug("Saving params into user");
        loginForm.setUserName(userName);
        loginForm.setPassword(password);
        return loginForm;
    }

    public static ProductForm createProductForm(HttpServletRequest req) {
        String priceFrom = req.getParameter(Constants.PRICE_FROM);
        String priceTo = req.getParameter(Constants.PRICE_TO);
        String productName = req.getParameter(Constants.PRODUCT_NAME);
        String[] manufacturer = req.getParameterValues(Constants.MANUFACTURER);
        String sortingType = req.getParameter(Constants.SORT_TYPE);
        String itemsNumberOnPage = req.getParameter(Constants.ITEMS_ON_PAGE);
        String pageNumber = req.getParameter(Constants.PAGE_NUMBER);
        String[] categories = req.getParameterValues(Constants.CATEGORIES);
        ProductForm productForm = new ProductForm();
        productForm.setSortingType(sortingType);
        productForm.setItemsNumberOnPage(itemsNumberOnPage);
        productForm.setPageNumber(pageNumber);
        productForm.setPriceFrom(priceFrom);
        productForm.setPriceTo(priceTo);
        productForm.setProductName(productName);
        productForm.setManufacturer(manufacturer);
        productForm.setCategories(categories);
        return productForm;
    }

}
