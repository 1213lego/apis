package com.lego.proapi.controller;

import com.lego.proapi.configuration.security.CustomUserDetail;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MainController {
    @PreAuthorize("authenticated")
    @PostMapping(value = "/login")
    public void login(@AuthenticationPrincipal CustomUserDetail activeUser) {
        log.info(activeUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin")
    public String admin() {
        return "ADMIN";
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/user")
    public String user() {
        return "user";
    }
    @PreAuthorize("hasRole('USER') and hasRole('ADMIN')")
    @GetMapping(value = "/user-admin")
    public String userAndAdmin() {
        return "/user-admin";
    }

}
