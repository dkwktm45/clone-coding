package com.hodoleg.clonecoding.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter @Getter
@ToString
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요") // 검증방식
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

}
