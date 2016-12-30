package com.epam.task11.captcha;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CaptchaImageGenerator {
    private int height = 25;
    private int width = 125;

    public CaptchaImageGenerator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public BufferedImage generateDefaultCaptcha(String token) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        String ch = token.substring(0, 6);
        Color c = new Color(0.6662f, 0.4569f, 0.3232f);
        GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
        graphics2D.setPaint(gp);
        Font font = new Font("Verdana", Font.CENTER_BASELINE, 26);
        graphics2D.setFont(font);
        graphics2D.drawString(ch, 2, 20);
        graphics2D.dispose();
        return image;
    }
}
