package com.epam.task11.servlets.func;

import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/avatar")
public class ImageDisplayerServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CaptchaServlet.class);
    private String avatarsStoragePath;
    private String productImagePath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        avatarsStoragePath = config.getServletContext().getInitParameter("AvatarImagePath");
        productImagePath = config.getServletContext().getInitParameter("ProductImagePath");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("ImageDisplayerServlet started");
        String imageName = req.getParameter("imageName");
        String type = req.getParameter("type");
        String imgPath;
        if ("avatar".equals(type)) {
            imgPath = avatarsStoragePath + imageName + "." + Constants.IMG_FORMAT;
        } else if ("product".equals(type)) {
            imgPath = productImagePath + imageName + "." + Constants.IMG_FORMAT;
        } else {
            return;
        }
        LOG.debug("Looking for file located = " + imgPath);
        File file = new File(imgPath);
        Image image = ImageIO.read(file);
        OutputStream out = resp.getOutputStream();
        ImageIO.write((RenderedImage) image, "jpg", out);
        out.flush();
        out.close();
        LOG.debug("Generating generated");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
