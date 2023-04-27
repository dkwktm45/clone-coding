package com.hodoleg.clonecoding.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class PostEdit {
    @NotBlank(message = "타이틀을 입력해주세요") // 검증방식
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    // 기능이 비슷하더라도 역할이 다르다면 class 분리를 해주자!
    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
