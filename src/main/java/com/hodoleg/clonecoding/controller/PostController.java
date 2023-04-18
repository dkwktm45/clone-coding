package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.request.PostEdit;
import com.hodoleg.clonecoding.request.PostSearch;
import com.hodoleg.clonecoding.response.PostResponse;
import com.hodoleg.clonecoding.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch){
        return postService.getList(postSearch);
    }
    @PatchMapping("/posts/{postId}")
    public void getList(@PathVariable(name = "postId") Long id , @RequestBody @Valid PostEdit postEdit){
        postService.edit(id , postEdit); // 가끔 응답을 넘겨주는 경우도 있다. 클라이언트에 따라 다름
    }
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable(name = "postId") Long id ){
        postService.delete(id);
    }
}

