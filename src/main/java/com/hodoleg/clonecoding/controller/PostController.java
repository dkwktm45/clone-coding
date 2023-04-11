package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.request.PostCreate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
public class PostController {

    // 웹 페이지 만드는 큰 방식
    // SSR -> jsp , thymeleaf, mustache , freemarker
    // html 랜더링 방식

    // SPA -> vue , nuxt ,next react
    // javascript + < - > api(json) 방식

    // 데이터를 검증하는 이유

    // 1. client 개발자가 깜박할 수 있다. 실수로 값을 안보낼 수 있다.
    // 2. client bug로 값이 누락될 수 있다.
    // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
    // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
    // 5. 서버 개발자의 편안함을 위해서
    @PostMapping("/posts")
    public Map<String, String> get(@RequestBody @Valid PostCreate params) {



        return Map.of();
    }
}

