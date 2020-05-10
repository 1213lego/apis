package org.lego.api2.controller;

import lombok.extern.log4j.Log4j2;
import org.lego.api2.client.Api1Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Log4j2
@RestController
public class ApiTest {
    private final RestTemplate restTemplate;
    private final Api1Client api1Client;

    public ApiTest(RestTemplate restTemplate, Api1Client api1Client) {
        this.restTemplate = restTemplate;
        this.api1Client = api1Client;
    }

    @GetMapping("/test")
    public String test() {
        log.info("/test");
        ResponseEntity<Object[]> forEntity = restTemplate.getForEntity("http://api/apps", Object[].class);
        for (Object a: forEntity.getBody()) {
        }
        return "OK";
    }

    @GetMapping("/test-feign")
    public String testfeign() {
        api1Client.apps();
        return "OK";
    }
}
