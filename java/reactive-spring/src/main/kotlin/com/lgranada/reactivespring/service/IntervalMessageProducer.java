package com.lgranada.reactivespring.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Component
public class IntervalMessageProducer {
    public Flux<GreetingsResponse> produceGreeting(String name) {
        return Flux
                .fromStream(Stream.generate(() -> "Hello " + name + " @ " + Instant.now()))
                .map(GreetingsResponse::new)
                .delayElements(Duration.ofSeconds(1));
    }
}
