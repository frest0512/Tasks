package com.epam.task7.sub2;

import com.epam.task7.sub2.transport.api.Transport;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        Transport restriction = new Factory().getTransport();
        restriction.setMaxSpeed(100);

        HashMap<String, Object> map = new HashMap<>();
        Transport mapRedirect = new Factory().getMap(map);
        mapRedirect.setMaxSpeed(100);
    }
}
