package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // 자칫 기존 컨트롤러에서 사용을 했기에 가능했다고 생각할 수 있지만
    // 응답 본문에 담는 역할을 하기 때문에 advice에도 넣어줘야한다.
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse("400", "잘못된 요청입니다.");

        for(FieldError fieldError : e.getFieldErrors()){
            errorResponse.addValidation(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return errorResponse;


    }
}
