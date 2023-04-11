package com.hodoleg.clonecoding.controller;

import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j @RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map<String, String> get(@RequestBody @Valid PostCreate request) {
        postService.write(request);
        return Map.of();
    }
}

