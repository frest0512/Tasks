package com.epam.task11.captcha;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CaptchaTextGenerator {

    private SecureRandom secureRandom;
    private int length;

    public CaptchaTextGenerator(String algoritm, int length) throws NoSuchAlgorithmException {
        secureRandom = SecureRandom.getInstance(algoritm);
        this.length = length;
    }

    public String generate() {
        StringBuffer captchaStringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(secureRandom.nextInt()) % 62;
            int charNumber;
            if (baseCharNumber < 26) {
                charNumber = 65 + baseCharNumber;
            } else if (baseCharNumber < 52) {
                charNumber = 97 + (baseCharNumber - 26);
            } else {
                charNumber = 48 + (baseCharNumber - 52);
            }
            captchaStringBuffer.append((char) charNumber);
        }
        return captchaStringBuffer.toString();
    }
}
