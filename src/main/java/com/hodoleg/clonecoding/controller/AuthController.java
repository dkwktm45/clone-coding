package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.config.AppConfig;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.response.TokenResponse;
import com.hodoleg.clonecoding.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/auth/login")
    public TokenResponse login(@RequestBody Login login){
        Long id = authService.login(login);

        /* 쿠키 방식
        ResponseCookie cookie = ResponseCookie.from("SESSION",accessToken)
                .domain("localhost") // todo 서버 환경에 따른 분리 필요
                .path("/")
                .httpOnly(true)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict") // todo SamSite에 대해 왜 쓰는지 알아두자
                .build();
        log.info(">>> cookie = {}",cookie.toString());
        */

        Key key = Keys.hmacShaKeyFor(appConfig.getJwtKey());

        String jws = Jwts.builder()
                .setSubject(String.valueOf(id))
                .signWith(key)
                .setIssuedAt(new Date())
                .compact();

        return new TokenResponse(jws);
    }
    @PostMapping("/auth/sign")
    public void sign(@RequestBody SignUp signUp){
        authService.signin(signUp);
    }
}
