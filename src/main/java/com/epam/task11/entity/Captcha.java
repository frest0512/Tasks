package com.epam.task11.entity;

import java.io.Serializable;

public class Captcha implements Serializable {
    private String code;
    private long time;

    public Captcha(String code, long time) {
        this.code = code;
        this.time = time;
    }

    public Captcha() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Captcha captcha = (Captcha) o;

        if (time != captcha.time) return false;
        return code != null ? code.equals(captcha.code) : captcha.code == null;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "code='" + code + '\'' +
                ", time=" + time +
                '}';
    }
}
