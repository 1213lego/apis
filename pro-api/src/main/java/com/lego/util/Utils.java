package com.lego.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class Utils {

    @SneakyThrows
    public static <D, S> void mergeNotNull(D d, S s) {
        Class<?> dClass = d.getClass();
        Class<?> sClass = s.getClass();
        Field[] sFields = sClass.getFields();
        for (Field field : sFields) {
            field.setAccessible(true);
            Object value = field.get(s);
            if (value != null) {
                dClass.getField(field.getName()).setAccessible(true);
                dClass.getField(field.getName()).set(d, value);
            }
        }
    }
}
