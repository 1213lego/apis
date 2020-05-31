package org.lego.api.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class Api {

    @Lazy
    private final EurekaClient eurekaClient;

    public Api(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @GetMapping("/apps")
    public List<Application> listInstances() {
        log.info("/apps");
        return eurekaClient.getApplications()
                .getRegisteredApplications();
    }
}
