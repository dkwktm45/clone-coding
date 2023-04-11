package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.request.PostCreate;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public Map<String, String> get(@RequestBody @Valid PostCreate params , BindingResult result) {
        if(result.hasErrors()){
            List<FieldError> filedErrorList = result.getFieldErrors();
            FieldError firstfieldError = filedErrorList.get(0);
            String fieldName = firstfieldError.getField();
            String fieldError = firstfieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fieldName,fieldError);
            return error;
        }
        /* vaild를 사용하자!!
        String title = params.getTitle();
        if(title == null || title.equals("")){
            // 이런식의 검증은 노가다성이다.
            // 이러한 노가다는 반복잡업으로 이루어질 수 있으며 이러한 경우 의심을 해봐야한다.
            // 누락성 가능 / 생각보다 검증해야할 부분들이 많다.
            throw new Exception("타이틀값이 없어요");
        }
        */

        return Map.of();
    }
}

