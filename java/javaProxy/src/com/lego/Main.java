package com.lego;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
    void walk();

    void talk();

    void run();
}

class Person implements Human {
    @Override
    public void walk() {
        System.out.println("Walking");
    }

    @Override
    public void talk() {
        System.out.println("Talking");
    }

    @Override
    public void run() {
        System.out.println("Running");
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;
    private Map<String, Integer> calls;

    public LoggingHandler(Object target) {
        this.target = target;
        calls = new HashMap<>();
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        calls.merge(method.getName(), 1, Integer::sum);
        if (method.getName().contains("toString")) {
            return calls.toString();
        }
        return method.invoke(target, objects);
    }
}

public class Main {
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf) {
        return (T) Proxy.newProxyInstance(itf.getClassLoader(),
                new Class[]{itf},
                new LoggingHandler(target));
    }

    private static String concatWithSeparator(String separator, String... strings) {
        if (strings.length == 0) return "";
        StringBuilder stringBuilder = new StringBuilder(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            stringBuilder.append(separator).append(strings[i]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(concatWithSeparator("-", "a"));
        System.out.println(concatWithSeparator("-", "a", "b"));
        System.out.println(concatWithSeparator("/"));
        // write your code here
        Person p = new Person();
        Human human = withLogging(p, Human.class);
        human.talk();
        human.talk();
        human.run();
        human.walk();
        System.out.println(human);
    }
}
