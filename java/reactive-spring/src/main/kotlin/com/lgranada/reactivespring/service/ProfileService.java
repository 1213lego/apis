package com.lgranada.reactivespring.service;

import com.lgranada.reactivespring.domain.Profile;
import com.lgranada.reactivespring.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public Flux<Profile> getProfiles() {
        return this.profileRepository.findAll();
    }

    public Mono<Profile> get(String id) {
        return this.profileRepository.findById(id);
    }

    public Mono<Profile> update(String id, String email) {
        return this.profileRepository
                .findById(id)
                .map(profile -> new Profile(profile.getId(), email))
                .flatMap(this.profileRepository::save);
    }

    public Mono<Profile> delete(String id) {
        return this.profileRepository
                .findById(id)
                .flatMap(p -> this.profileRepository.deleteById(p.getId()).thenReturn(p));
    }
}
