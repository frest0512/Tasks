package com.epam.task5;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        FileWrapper fileWrapper = new FileWrapper<>("readme.txt");
        Iterator<String> iterator = fileWrapper.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
