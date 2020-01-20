package com.lego.bestJava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BeanProvider {
    @Bean
    @Scope("singleton")
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(4);
    }
}
