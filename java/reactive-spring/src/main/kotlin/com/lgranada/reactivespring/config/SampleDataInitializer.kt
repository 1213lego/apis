package com.lgranada.reactivespring.config

import com.lgranada.reactivespring.domain.Profile
import com.lgranada.reactivespring.repository.ProfileRepository
import com.lgranada.reactivespring.service.ProfileService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class SampleDataInitializer(val profileRepository: ProfileRepository) : ApplicationListener<ApplicationReadyEvent> {
    companion object {
        val log: Logger = LogManager.getLogger(ProfileService::class.java)
    }
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        profileRepository
                .deleteAll()
                .thenMany(
                        Flux.range(65,6)
                                .map { num -> num.toChar().toLowerCase() }
                                .map { name -> Profile("${name}@gmail.com") }
                                .flatMap(profileRepository::save)
                )
                .thenMany(profileRepository.findAll())
                .subscribe { profile -> log.info("Saved ${profile}") }
    }

}
