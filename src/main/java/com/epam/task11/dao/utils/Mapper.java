package com.epam.task11.dao.utils;

import com.epam.task11.annotations.DBField;
import com.epam.task11.exeptions.MAPPERException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Mapper {
    private static final Logger LOG = LogManager.getLogger(Mapper.class);

    public static <T> T mapp(Field[] fields, T obj, ResultSet resultSet) {
        Arrays.stream(fields).filter(field1 -> field1.isAnnotationPresent(DBField.class)).forEach(field -> {
            field.setAccessible(true);
            String filedName = field.getAnnotation(DBField.class).DBFieldName();
            try {
                field.set(obj, resultSet.getObject(filedName));
            } catch (IllegalAccessException | SQLException e) {
                LOG.warn("unmapReflect Exception + " + e);
                throw new MAPPERException(e);
            }
        });
        return obj;
    }
}
