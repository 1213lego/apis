package com.lgranada.reactivespring.controller

import com.lgranada.reactivespring.domain.Profile
import com.lgranada.reactivespring.service.GreetingsRequest
import com.lgranada.reactivespring.service.GreetingsResponse
import com.lgranada.reactivespring.service.IntervalMessageProducer
import com.lgranada.reactivespring.service.ProfileService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class RouterFuctional(val profileService: ProfileService, val intervalMessageProducer: IntervalMessageProducer) {
    @Bean
    fun getProfiles() = router {
        GET("/profileS") { request: ServerRequest -> ServerResponse.ok().body(profileService.getProfiles(), Profile::class.java) }
    }

    @Bean
    fun stringPublisher() = router {
        GET("/sse/greeting/{name}", this@RouterFuctional::handleStringPublisher)
    }

    fun handleStringPublisher(request: ServerRequest): Mono<ServerResponse>{
        var greetingResponse: Flux<GreetingsResponse> = this.intervalMessageProducer.produceGreeting(request.pathVariable("name"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(greetingResponse,GreetingsResponse::class.java);
    }
}
