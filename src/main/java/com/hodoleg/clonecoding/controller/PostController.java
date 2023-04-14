package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.response.PostResponse;
import com.hodoleg.clonecoding.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.*;



@Slf4j @RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void get(@RequestBody @Valid PostCreate request) {
        // 때에 따라 다르지만 클라이언트쪽에 응답으로 저장을 알려주는 케이스가 있다.
        // case1 저장데이터를 넘겨주는겨웅
        // case2 id를 넘겨주는경우
        // case3 응답 필요가 없음 --> post글 데이터 저장하는 경우
        postService.write(request);
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징 처리)
     * /posts/{postId} -> 글 한개만 조회
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long id){
        return postService.get(id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(){
        return postService.getList();
    }
    
}

