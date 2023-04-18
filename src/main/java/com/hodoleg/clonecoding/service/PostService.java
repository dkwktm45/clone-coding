package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.domain.PostEditor;
import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.request.PostEdit;
import com.hodoleg.clonecoding.request.PostSearch;
import com.hodoleg.clonecoding.response.PostResponse;
import com.hodoleg.clonecoding.respository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public void write(PostCreate postCreate){
        Post post = Post.builder().title(postCreate.getTitle()).content(postCreate.getContent()).build();
        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle()).build();
    }

    public List<PostResponse> getList(PostSearch postSearch){
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void edit(Long id, PostEdit postEdit){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder.title(postEdit.getTitle()).content(postEdit.getContent()).build();

        post.edit(postEditor);

        postRepository.save(post);
    }
}
