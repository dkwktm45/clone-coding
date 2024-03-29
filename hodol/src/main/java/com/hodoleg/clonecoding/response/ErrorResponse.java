package com.hodoleg.clonecoding.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * {
 *     "code" : "400"
 *     "message" : "잘못된 요청입니다.",
 *     "validation" : {
 *         "title : "제목이 없습니다."
 *     }
 * }*/
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> validation ;
    public void addValidation(String filedName, String errorMessage){
        validation.put(filedName,errorMessage);
    }
    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }
}

