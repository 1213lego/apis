package com.lgranada.reactivespring.controller;

import com.lgranada.reactivespring.service.GreetingsRequest;
import com.lgranada.reactivespring.service.GreetingsResponse;
import com.lgranada.reactivespring.service.IntervalMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@AllArgsConstructor
public class RSocketGreetingController {
    private final IntervalMessageProducer intervalMessageProducer;
    @MessageMapping("rsgreenting")
    Flux<GreetingsResponse> greet(GreetingsRequest request){
        return intervalMessageProducer.produceGreeting(request.getName());
    }
}
