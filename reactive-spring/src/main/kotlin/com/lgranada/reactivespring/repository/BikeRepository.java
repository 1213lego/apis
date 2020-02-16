package com.lgranada.reactivespring.repository;

import com.lgranada.reactivespring.domain.Bike;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BikeRepository extends ReactiveMongoRepository<Bike, String> {
}
