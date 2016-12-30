package com.epam.task11.captcha.handlers;

import com.epam.task11.captcha.CaptchaTextGenerator;
import com.epam.task11.entity.Captcha;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptchaHandler extends CaptchaHandler {
    private static final Logger LOG = LogManager.getLogger(SessionCaptchaHandler.class);
    private long timeLimit;

    public SessionCaptchaHandler(long timeLimit, CaptchaTextGenerator captchaTextGenerator) {
        super(captchaTextGenerator);
        this.timeLimit = timeLimit;
    }

    @Override
    public boolean validate(HttpServletRequest request, String str) {
        Object rightKey = request.getSession().getAttribute(Constants.CAPTCHA_CODE_TO_PUT_INTO_HIDDEN);
        LOG.debug("Validation expected = " + rightKey + " got = " + str);
        if (rightKey == null || str == null) {
            return false;
        }
        Captcha captcha = (Captcha) rightKey;
        if (System.currentTimeMillis() - captcha.getTime() > timeLimit) {
            return false;
        }
        return captcha.getCode().equals(str);
    }

    @Override
    public void saveCode(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        request.getSession().setAttribute(Constants.CAPTCHA_CODE_TO_PUT_INTO_HIDDEN, captcha);
    }

    @Override
    public String getCode(HttpServletRequest request, String UUID) {
        return ((Captcha) request.getSession().getAttribute(Constants.CAPTCHA_CODE_TO_PUT_INTO_HIDDEN)).getCode();
    }
}
