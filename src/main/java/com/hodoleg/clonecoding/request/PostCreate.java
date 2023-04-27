package com.hodoleg.clonecoding.request;


import com.hodoleg.clonecoding.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요") // 검증방식
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void validate(){
        if(title.contains("바보")){
            throw new InvalidRequest("title", "타이틀에 바보를 포함할 수 없습니다.");
        }
    }
}
