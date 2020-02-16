package com.lgranada.reactivespring.service;

import com.lgranada.reactivespring.domain.Profile;
import com.lgranada.reactivespring.repository.BikeRepository;
import com.lgranada.reactivespring.repository.ProfileRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Component
@Log4j2
public class IntervalMessageProducer {
    private final BikeRepository bikeRepository;
    private final ProfileRepository profileRepository;

    public IntervalMessageProducer(BikeRepository bikeRepository, ProfileRepository profileRepository) {
        this.bikeRepository = bikeRepository;
        this.profileRepository = profileRepository;
        profileRepository
                .deleteAll()
                .then(bikeRepository.deleteAll())
                .thenMany(Flux.
                        range(1, 5)
                        .map(num -> new Profile((char) num.intValue() + "@gmail.com"))
                        .flatMap(profileRepository::save)
                ).subscribe();

    }

    public Flux<GreetingsResponse> produceGreeting(String name) {
        return Flux
                .fromStream(Stream.generate(() -> "Hello " + name + " @ " + Instant.now()))
                .map(GreetingsResponse::new)
                .delayElements(Duration.ofSeconds(3));
    }
}
