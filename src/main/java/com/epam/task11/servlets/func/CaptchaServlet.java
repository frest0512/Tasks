package com.epam.task11.servlets.func;

import com.epam.task11.captcha.CaptchaImageGenerator;
import com.epam.task11.captcha.handlers.CaptchaHandler;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CaptchaServlet.class);
    private CaptchaImageGenerator captchaImageGenerator;
    private CaptchaHandler captchaHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        captchaImageGenerator = (CaptchaImageGenerator) config.getServletContext().getAttribute(Constants.CAPTCHA_IMAGE_GENERATION);
        captchaHandler = (CaptchaHandler) config.getServletContext().getAttribute(Constants.CAPTCHA_HANDLER_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Generating captcha");
        String code = req.getParameter("code");
        LOG.debug("Captcha map.key = " + code);
        String captcha = captchaHandler.getCode(req, code);
        LOG.debug("Captcha key = " + captcha);
        BufferedImage image = captchaImageGenerator.generateDefaultCaptcha(captcha);
        LOG.debug("Saving captcha");
        OutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
        out.close();
        LOG.debug("Generating generated");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}