package com.lego.proapi.controller;

import com.lego.proapi.configuration.security.CustomUserDetail;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @PreAuthorize("authenticated")
    @PostMapping(value = "/login")
    public void login(@AuthenticationPrincipal CustomUserDetail activeUser) {
        System.out.println(activeUser);
    }
}
