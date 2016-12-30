package com.epam.task3.strings;

import java.util.Arrays;

public class StringHashOnLength {

    private char[] str;

    public StringHashOnLength(String string) {
        str = string.toCharArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringHashOnLength that = (StringHashOnLength) o;
        return Arrays.equals(str, that.str);

    }

    @Override
    public int hashCode() {
        return (str != null) ? str.length : 0;
    }

    @Override
    public String toString() {
        return new String(str);
    }
}
