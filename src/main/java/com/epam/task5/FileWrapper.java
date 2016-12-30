package com.epam.task5;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileWrapper<T extends String> implements Iterable {
    private static final String FILE_ENCODING = "file.encoding";
    private File file;

    public FileWrapper(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        if (!file.isFile()) {
            throw new FileNotFoundException();
        }

    }

    @Override
    public Iterator iterator() {
        return new FileIterator();
    }


    private class FileIterator implements Iterator<String> {

        private BufferedReader bufferedReader;

        public FileIterator() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), System.getProperty(FILE_ENCODING)));
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                System.err.println(e);
            }
        }

        @Override
        public boolean hasNext() {
            try {
                return bufferedReader.ready();
            } catch (IOException e) {
                return false;
            }

        }

        @Override
        public String next() {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new NoSuchElementException();
            }
        }
    }
}
