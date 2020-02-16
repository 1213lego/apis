package com.lego.bestJava.controllers;

import com.google.common.collect.ImmutableList;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController("/")
public class Home {
    private ExecutorService executor;

    @Autowired
    public Home(ExecutorService executor) {
        this.executor = executor;
    }

    @GetMapping(value = "/best")
    public String hello() {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> words = ImmutableList.of("1", "2", "3", "4", "5", "dsadf");
        CompletableFuture<String> futureString = produceFutureString("dfdsfdsf");
        words.stream()
                .parallel()
                .filter(this::isValidNumber)
                .map(Integer::parseInt)
                .forEach(stringBuffer::append);
        return stringBuffer.toString();
    }

    private CompletableFuture<String> produceFutureString(String value) {
        return CompletableFuture.supplyAsync(() -> String.format("%s - %s\n", value, "hello"), executor);
    }

    private boolean isValidNumber(String possibleNumber) {
        return Try.of(() -> Integer.valueOf(possibleNumber)).isSuccess();
    }
}

