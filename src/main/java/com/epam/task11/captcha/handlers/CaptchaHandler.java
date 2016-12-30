package com.epam.task11.captcha.handlers;

import com.epam.task11.captcha.CaptchaTextGenerator;
import com.epam.task11.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CaptchaHandler {
    private CaptchaTextGenerator captchaTextGenerator;

    public CaptchaHandler(CaptchaTextGenerator captchaTextGenerator) {
        this.captchaTextGenerator = captchaTextGenerator;
    }

    public abstract boolean validate(HttpServletRequest request, String printedKey);

    public abstract void saveCode(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    public abstract String getCode(HttpServletRequest request, String UUID);

    public void createAndSave(HttpServletRequest request, HttpServletResponse response) {
        String code = captchaTextGenerator.generate();
        Captcha captcha = new Captcha();
        captcha.setCode(code);
        captcha.setTime(System.currentTimeMillis());
        saveCode(request, response, captcha);
    }
}
