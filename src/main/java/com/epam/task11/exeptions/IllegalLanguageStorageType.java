package com.epam.task11.exeptions;

public class IllegalLanguageStorageType extends RuntimeException {
    public IllegalLanguageStorageType(String s) {
        super(s);
    }
}
