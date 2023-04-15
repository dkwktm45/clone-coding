package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.response.PostResponse;
import com.hodoleg.clonecoding.respository.PostRepository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }
    @Test
    @DisplayName("글 작성")
    void test1(){
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        //when
        postService.write(postCreate);

        //then
        Assertions.assertEquals(1L,postRepository.findAll().get(0).getId());
    }
    @Test
    @DisplayName("글 1개 조회")
    void test2(){
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
        assertEquals(1L,postRepository.count());
        assertEquals("foo",post.getTitle());
        assertEquals("bar",post.getContent());
    }
    @Test
    @DisplayName("글 1페이지 조회")
    void test3(){
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                                    .mapToObj(i -> {
                                        return Post.builder()
                                            .title("호돌맨 제목 " + i)
                                            .content("호돌맨 내용 " + i).build();
                                    }).collect(Collectors.toList());
        
        postRepository.saveAll(requestPosts);
        Pageable pageable = PageRequest.of(0, 5,Sort.by(Sort.Direction.DESC,"id"));
        //when
        List<PostResponse> post = postService.getList(pageable);

        //then
        assertEquals(5L,post.size());
        assertEquals("호돌맨 제목 30", post.get(0).getTitle());
        assertEquals("호돌맨 제목 26", post.get(4).getTitle());
    }

}