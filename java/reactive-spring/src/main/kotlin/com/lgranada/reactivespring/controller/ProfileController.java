package com.lgranada.reactivespring.controller;

import com.lgranada.reactivespring.domain.Profile;
import com.lgranada.reactivespring.service.ProfileService;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/profiles")
public class ProfileController {
    private ProfileService profileService;
    private static final MediaType mediaType = MediaType.APPLICATION_JSON;

    @GetMapping
    public Publisher<Profile> getAll() {
        return profileService.getProfiles();
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<Profile>> getById(@PathVariable(value = "id") String id) {
        return profileService.get(id)
                .map(profile -> new ResponseEntity<>(profile, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    Publisher<Profile> deleteById(@PathVariable String id) {
        return profileService.delete(id);
    }

    @PutMapping("/{id}")
    Publisher<ResponseEntity<Profile>> updateById(@PathVariable String id, @RequestBody Profile profile) {
        return Mono
                .just(profile)
                .flatMap(p -> profileService.update(id, p.getEmail()))
                .map(p -> ResponseEntity
                        .ok()
                        .contentType(mediaType)
                        .build());
    }
}
