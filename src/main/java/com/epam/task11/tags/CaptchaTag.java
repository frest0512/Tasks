package com.epam.task11.tags;

import com.epam.task11.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CaptchaTag extends SimpleTagSupport {

    private static final Logger LOG = LogManager.getLogger(CaptchaTag.class);

    private String imageServletUrl;
    private String key;

    public CaptchaTag() {
    }

    public String getImageServletUrl() {
        return imageServletUrl;
    }

    public void setImageServletUrl(String imageServletUrl) {
        this.imageServletUrl = imageServletUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void doTag() throws JspException, IOException {
        LOG.debug("Captcha tag started");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getImgTag());
        if (key != null && key.length() > 0) {
            stringBuilder.append(System.lineSeparator()).append(getHiddenField());
        }
        getJspContext().getOut().write(stringBuilder.toString());
        LOG.debug("Captcha tag finished");
    }

    private String getImgTag() {
        return String.format("<img src=\"%s?code=%s\" alt=\"Captcha\">", imageServletUrl, key);
    }

    private String getHiddenField() {
        return String.format("<input name=\"" + Constants.USER_CAPTCHA_CODE_HIDDEN + "\"type=\"hidden\" value=\"%s\">", key);
    }
}
