package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.request.PostEdit;
import com.hodoleg.clonecoding.request.PostSearch;
import com.hodoleg.clonecoding.response.PostResponse;
import com.hodoleg.clonecoding.respository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        //when
        postService.write(postCreate);

        //then
        Assertions.assertEquals(1L, postRepository.findAll().get(0).getId());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        //when
        PostResponse post = postService.get(requestPost.getId());

        //then
        assertNotNull(post);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", post.getTitle());
        assertEquals("bar", post.getContent());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {
        //given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("호돌맨 제목 " + i)
                            .content("호돌맨 내용 " + i).build();
                }).collect(Collectors.toList());

        postRepository.saveAll(requestPosts);
        PostSearch postSearch = PostSearch.builder().page(1).size(10).build();
        //when
        List<PostResponse> post = postService.getList(postSearch);

        //then
        assertEquals(10L, post.size());
        assertEquals("호돌맨 제목 19", post.get(0).getTitle());
    }

    @Test
    @DisplayName("글 수정")
    void test4() {
        //given
         Post post = Post.builder()
                .title("호돌맨 제목")
                .content("호돌맨 내용").build();


        postRepository.save(post);
        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정")
                .content(null)
                .build();
        //when
        postService.edit(post.getId(),postEdit);
        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않습니다. id = " + post.getId()));
        assertEquals("제목 수정",changePost.getTitle());

    }

    @Test
    @DisplayName("글 수정 - content 값이 null인 경우")
    void test5() {
        //given
        Post post = Post.builder()
                .title("호돌맨 제목")
                .content("호돌맨 내용").build();


        postRepository.save(post);
        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정")
                .content(null)
                .build();
        //when
        postService.edit(post.getId(),postEdit);
        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않습니다. id = " + post.getId()));
        assertEquals("제목 수정",changePost.getTitle());
        assertEquals("호돌맨 내용",changePost.getContent());
    }
    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        //given
        Post post = Post.builder()
                .title("호돌맨 제목")
                .content("호돌맨 내용").build();


        postRepository.save(post);

        //when
        postService.delete(post.getId());
        //then
        assertEquals(0,postRepository.count());
    }

}