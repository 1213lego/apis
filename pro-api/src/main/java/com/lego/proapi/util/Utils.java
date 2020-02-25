package com.lego.proapi.util;

import io.vavr.control.Try;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    @SneakyThrows
    private static <S, T> T map(S source,
                                T target,
                                Map<String, Function<S, Object>> customTransform,
                                Set<String> ignoreFields) {
        Class sourceType = source.getClass();
        Class targetType = target.getClass();
        List<Field> sourceFields = Stream.of(sourceType.getDeclaredFields())
                .filter((field -> !ignoreFields.contains(field.getName())))
                .collect(Collectors.toList());
        for (Field field : sourceFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(source);
            if (fieldValue == null) continue;
            Field targetField = Try.of(() -> targetType.getDeclaredField(field.getName())).getOrNull();
            if (targetField == null) continue;
            targetField.setAccessible(true);
            fieldValue = Optional.ofNullable(customTransform.get(field.getName()))
                    .map((function) -> function.apply(source))
                    .orElse(fieldValue);
            targetField.set(target, fieldValue);
        }
        return target;
    }

    @SneakyThrows
    public static <S, T> T map(S source,
                               Class<T> targetType,
                               Map<String, Function<S, Object>> customTransform,
                               Set<String> ignoreFields) {
        return map(source, targetType.getDeclaredConstructor().newInstance(), customTransform, ignoreFields);
    }

    @SneakyThrows
    public static <S, T> T map(S source,
                               Class<T> targetType,
                               Map<String, Function<S, Object>> customTransform) {
        return map(source, targetType.getDeclaredConstructor().newInstance(), customTransform, new HashSet<>());
    }

    @SneakyThrows
    public static <S, T> T map(S source, Class<T> targetType) {
        return map(source, targetType.getDeclaredConstructor().newInstance(), new HashMap<>(), new HashSet<>());
    }

    @SneakyThrows
    public static <S, T> void merge(S source, T target) {
        map(source, target, new HashMap<>(), new HashSet<>());
    }
}
