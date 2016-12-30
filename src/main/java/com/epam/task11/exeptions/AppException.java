package com.epam.task11.exeptions;

public class AppException extends RuntimeException {
    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String s) {
        super(s);
    }
}
