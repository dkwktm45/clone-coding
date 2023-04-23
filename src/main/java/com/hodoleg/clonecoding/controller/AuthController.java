package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.response.SessionResponse;
import com.hodoleg.clonecoding.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login){
        return authService.signin(login);
    }
}
