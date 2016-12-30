package com.epam.task11.captcha.handlers;

import com.epam.task11.captcha.CaptchaTextGenerator;
import com.epam.task11.entity.Captcha;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class CookieCaptchaHandler extends CaptchaHandler implements Cleanable {
    private static final Logger LOG = LogManager.getLogger(CookieCaptchaHandler.class);

    private Map<String, Captcha> map;
    private long timeLimit;

    public CookieCaptchaHandler(Map<String, Captcha> map, long timeLimit, CaptchaTextGenerator captchaTextGenerator) {
        super(captchaTextGenerator);
        this.timeLimit = timeLimit;
        this.map = map;

    }

    @Override
    public boolean validate(HttpServletRequest request, String printedKey) {
        String realKey = getCaptchaKeyFromCookie(request);
        if (printedKey == null || realKey == null || map == null || map.get(realKey) == null) {
            LOG.debug("Wrong captcha expected = " + map.get(realKey) + ", got = " + printedKey);
            return false;
        }
        LOG.debug("Captcha: expected = " + map.get(realKey).getCode() + ", got = " + printedKey);
        return printedKey.equals(map.get(realKey).getCode());
    }

    @Override
    public void saveCode(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        String genKey = genUniqueKey(map);
        map.put(genKey, captcha);
        Captcha cap = new Captcha();
        cap.setCode(genKey);
        request.setAttribute(Constants.CAPTCHA_CODE_TO_PUT_INTO_HIDDEN, cap);
        Cookie cookie = new Cookie(Constants.CAPTCHA_CODE_COOKIE, genKey);
        response.addCookie(cookie);

    }

    @Override
    public String getCode(HttpServletRequest request, String UUID) {
        return map.get(UUID).getCode();
    }


    public String getCaptchaKeyFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cook = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(Constants.CAPTCHA_CODE_COOKIE)).findFirst().orElse(null);
        return (cook != null) ? cook.getValue() : null;
    }

    public String genUniqueKey(Map<String, Captcha> map) {
        Random random = new Random();
        int key = random.nextInt(999999999);
        while (map.containsKey(key = random.nextInt(999999999))) ;
        return String.valueOf(key);
    }

    @Override
    public void clean() {
        LOG.debug("Cleaning started before = " + map.size());
        int deletedItems = 0;
        for (Map.Entry<String, Captcha> entry : map.entrySet()) {
            if (System.currentTimeMillis() - entry.getValue().getTime() > timeLimit) {
                map.remove(entry.getKey());
                deletedItems++;
            }
        }
        LOG.debug("Cleaning: deleted = " + deletedItems);
        LOG.debug("Cleaning finished after = " + map.size());
    }

}
