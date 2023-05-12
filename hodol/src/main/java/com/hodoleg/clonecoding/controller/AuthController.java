package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.config.AppConfig;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.response.TokenResponse;
import com.hodoleg.clonecoding.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;

@RestController @Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;
    @GetMapping("/auth/login")
    public String login(){
        return "로그인 페이지입니다.";
    }
    @GetMapping("/")
    public String main(){
        return "main 페이지입니다.";
    }
    @PostMapping("/auth/sign")
    public void sign(@RequestBody SignUp signUp){
        authService.signin(signUp);
    }
}
