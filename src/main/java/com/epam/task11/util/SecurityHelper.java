package com.epam.task11.util;


import com.epam.task11.exeptions.AppException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SecurityHelper {
    private static final Logger LOG = LogManager.getLogger(SecurityHelper.class);

    public static String getHash(String input) {
        String algorithm = "SHA-1";
        MessageDigest md = null;
        String hex = null;
        try {
            LOG.debug("Try to fing such algorithm : " + algorithm);
            md = MessageDigest.getInstance(algorithm);
            LOG.debug("The algorithm was found");
            md.reset();
            LOG.debug("Trying to get the bytes in the UTF-8 encoding from input password = " + input);
            byte[] buffer = input.getBytes(Charset.forName("UTF-8"));
            LOG.debug("Bytes have been received");
            md.update(buffer);
            LOG.debug("Trying to encrypt  this bytes" + Arrays.toString(buffer) + " with this algorithm " + algorithm);
            byte[] digest = md.digest();
            LOG.debug("Ecnrypt done " + Arrays.toString(digest));
            StringBuilder hexStr = new StringBuilder();
            LOG.debug("Trying to display these bytes in hexadecimal." + " Bytes: " + Arrays.toString(digest));
            for (int i = 0; i < digest.length; i++) {
                hexStr.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            hex = hexStr.toString();
            LOG.debug("hashed data using the algorithm  " + algorithm + " in hexadecimal form :" + hex);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(input + " with this algorithm " + algorithm);
            throw new AppException("Error getting password hash");
        }
        return hex;
    }
}
