package com.epam.task11.exeptions;

public class DAOException extends RuntimeException {
    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String s) {
        super(s);
    }
}
