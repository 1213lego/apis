package com.lgranada.reactivespring.repository;

import com.lgranada.reactivespring.domain.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface ProfileRepository : ReactiveMongoRepository<Profile, String> {
}
