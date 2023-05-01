package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.config.data.UserSession;
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

    @GetMapping("/foo")
    public Long foo(UserSession userSession){
        return userSession.id;
    }
    @PostMapping("/posts")
    public void get(@RequestBody @Valid PostCreate request) {
            request.validate();
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

