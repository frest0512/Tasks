package com.epam.task3.strings;

import java.util.Arrays;

public class StringHashOnFirst4Chars {

    private char[] str;

    public StringHashOnFirst4Chars(String string) {
        str = string.toCharArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringHashOnFirst4Chars that = (StringHashOnFirst4Chars) o;
        return Arrays.equals(str, that.str);

    }

    @Override
    public int hashCode() {
        if (str != null) {
            return (str.length < 4) ? Arrays.hashCode(str) : str[0] + str[1] + str[2] + str[3];
        }
        return 0;

    }

    @Override
    public String toString() {
        return new String(str);
    }
}
