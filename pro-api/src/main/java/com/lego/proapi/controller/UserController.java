package com.lego.proapi.controller;

import com.lego.proapi.configuration.security.CustomUserDetail;
import com.lego.proapi.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signUp() {
    }
    @PostMapping(value = "/login")
    public void login(@AuthenticationPrincipal CustomUserDetail activeUser) {
        log.info(activeUser);
    }
}
