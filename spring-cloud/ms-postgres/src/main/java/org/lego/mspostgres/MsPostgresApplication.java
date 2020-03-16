package org.lego.mspostgres;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@Log4j2
public class MsPostgresApplication {
    @Value("${messages.error}")
    private String erroMessage;
    @Value("${spring.application.name}")
    private String applicationName;
    public static void main(String[] args) {
        SpringApplication.run(MsPostgresApplication.class, args);
    }

}
