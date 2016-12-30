package com.epam.task11.captcha;

import com.epam.task11.captcha.handlers.Cleanable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CaptchaCleaner implements Runnable {
    private static final Logger LOG = LogManager.getLogger(CaptchaCleaner.class);
    private Cleanable cleaner;

    public CaptchaCleaner(Cleanable cleaner) {
        this.cleaner = cleaner;
    }

    @Override
    public void run() {
        LOG.debug("Cleaning captcha started");
        cleaner.clean();
    }
}
