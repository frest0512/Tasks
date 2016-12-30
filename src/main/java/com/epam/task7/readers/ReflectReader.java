package com.epam.task7.readers;

import com.epam.task1.entity.Transport;
import com.epam.task6.readers.Reader;
import com.epam.task6.scanners.MyScanner;
import com.epam.task7.anotations.FieldToRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ResourceBundle;

public abstract class ReflectReader extends Reader {

    private ResourceBundle rs;
    private MyScanner scanner;

    public ReflectReader(ResourceBundle rs, MyScanner scanner) {
        this.rs = rs;
        this.scanner = scanner;
    }

    @Override
    protected void fill(Transport transport) {
        Class aClass = transport.getClass();
        filter(transport, aClass);
    }

    public void filter(Transport transport, Class cl) {
        Class clazz = cl;
        do {
            Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(FieldToRequest.class))
                    .forEach(field -> {
                        field.setAccessible(true);
                        System.out.println("Enter field => " + rs.getString(field.getDeclaredAnnotation(FieldToRequest.class).key()));
                        String obj = null;
                        if (scanner.hasNext()) {
                            obj = scanner.next();
                        }

                        if (field.getType() == String.class) {
                            try {
                                field.set(transport, obj);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                Method method = field.get(transport).getClass().getMethod("valueOf", String.class);
                                field.set(transport, method.invoke(transport, obj));
                            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                    });
        } while ((clazz = clazz.getSuperclass()) != Object.class);
    }

}
