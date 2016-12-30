package com.epam.task11.captcha.handlers;

import com.epam.task11.captcha.CaptchaTextGenerator;
import com.epam.task11.entity.Captcha;
import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;

public class HiddenFieldCaptchaHandler extends CaptchaHandler implements Cleanable {
    private static final Logger LOG = LogManager.getLogger(HiddenFieldCaptchaHandler.class);
    private Map<String, Captcha> map;
    private long timeLimit;

    public HiddenFieldCaptchaHandler(Map<String, Captcha> map, long timeLimit, CaptchaTextGenerator captchaTextGenerator) {
        super(captchaTextGenerator);
        this.timeLimit = timeLimit;
        this.map = map;
    }

    @Override
    public boolean validate(HttpServletRequest request, String printedKey) {
        String str = request.getParameter(Constants.USER_CAPTCHA_CODE_HIDDEN);
        if (printedKey == null || map == null || map.get(str) == null || map.get(str).getCode() == null) {
            LOG.debug("Wrong captcha expected = " + map.get(str) + ", got = " + printedKey);
            return false;
        }
        LOG.debug("Captcha: expected = " + map.get(str).getCode() + ", got = " + printedKey);
        return printedKey.equals(map.get(str).getCode());
    }

    @Override
    public void saveCode(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        String genKey = genUniqueKey(map);
        Captcha cap = new Captcha();
        cap.setCode(genKey);
        request.setAttribute(Constants.CAPTCHA_CODE_TO_PUT_INTO_HIDDEN, cap);
        map.put(genKey, captcha);
    }

    @Override
    public String getCode(HttpServletRequest request, String UUID) {
        return map.get(UUID).getCode();
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
