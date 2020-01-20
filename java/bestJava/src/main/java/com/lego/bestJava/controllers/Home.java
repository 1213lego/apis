package com.lego.bestJava.controllers;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "non-thread-safe")
    public String helloNonSafe() {
        StringBuilder builder = new StringBuilder();
        List<CompletableFuture<String>> allFutureStrings = IntStream.range(0, 1000)
                .mapToObj(String::valueOf)
                .sequential()
                .map(this::produceFutureString)
                .map((future) -> future.whenComplete((value, throwable) -> builder.append(value)))
                .collect(Collectors.toList());
        //Sincrono - codigo bloqueante
        allFutureStrings.forEach((future) -> System.out.println(future.join()));
        return builder.toString();
    }

    private CompletableFuture<String> produceFutureString(String value) {
        return CompletableFuture.supplyAsync(() -> String.format("%s - %s\n", value, "hello"));
    }

    @GetMapping(value = "thread-safe")
    public String helloSafe() {
        StringBuffer builder = new StringBuffer();
        List<CompletableFuture<String>> allFutureStrings = IntStream.range(0, 1000)
                .mapToObj(String::valueOf)
                .sequential()
                .map(this::produceFutureString)
                .map((future) -> future.whenComplete((value, throwable) -> builder.append(value)))
                .collect(Collectors.toList());
        //Sincrono - codigo bloqueante
        allFutureStrings.forEach((future) -> System.out.println(future.join()));
        return builder.toString();
    }
    @GetMapping(value = "/best")
    public String bestPractices() {
        StringBuffer builder = new StringBuffer();
        List<String> words = ImmutableList.of("1", "2", "3", "4");
        words.stream()
                .map(this::convertoToNumber)
                .forEach((value) -> builder.append(value));
        return builder.toString();
    }
    private Integer convertoToNumber(String value) {
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e ){
            return -1;
        }
    }
}
