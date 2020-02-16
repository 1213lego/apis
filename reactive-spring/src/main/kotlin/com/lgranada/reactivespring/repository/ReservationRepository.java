package com.lgranada.reactivespring.repository;

import com.lgranada.reactivespring.domain.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
}
