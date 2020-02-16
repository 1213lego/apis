package com.lgranada.reactivespring.controller;

import com.lgranada.reactivespring.domain.Reservation;
import com.lgranada.reactivespring.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Configuration
@AllArgsConstructor
public class RouterFuctionalJ {

    private ReservationRepository reservationRepository;

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .GET("/reservations", request -> ok().body(reservationRepository.findAll(), Reservation.class))
                .POST("/reservations", this::handleInsert)
                .build();
    }

    @NotNull
    private Mono<ServerResponse> handleInsert(ServerRequest request) {
        Mono<Reservation> saved = request.bodyToMono(Reservation.class)
                .flatMap(reservationRepository::save);
        return status(HttpStatus.CREATED).body(saved, Reservation.class);
    }
}
