package com.hodoleg.clonecoding.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
    /**
     * 빌더의 장점
     *  - 가독성에 좋다. (생성자가 여러개가 있을때)
     *  - 필요한 값만 받을 수 있다.
     *  - 객체의 불변성
     */
}
