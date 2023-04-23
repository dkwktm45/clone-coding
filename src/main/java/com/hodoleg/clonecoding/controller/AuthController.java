package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.exception.InvalidSigninInformation;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/auth/login")
    public AuthUser login(@RequestBody Login login){
        // json 아이디/비밀번호
        log.info(">>> login {}",login);
        // DB에서 조회
        AuthUser authUser = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        // 토큰 인증방식
        return authUser;

    }
}
