package com.hodoleg.clonecoding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.respository.PostRepository;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;


@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }
    @Test
    @DisplayName("/posts 요청시 데이터 저장")
    void test() throws Exception{
        //given
        PostCreate req = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(req);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception{
        //given
        PostCreate req = PostCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(req);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다.")
    void test3() throws Exception{
        //given
        PostCreate req = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(req);
        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }
    @Test
    @DisplayName("/posts/{postId} 글 한개 조회")
    void test4() throws Exception{
        //given
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(post);

        // expected
        mockMvc.perform(get("/posts/{postId}",post.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("foo"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());

    }

    @Test
    @DisplayName("글 여러개 조회")
    void test5() throws Exception{
        //given
        Post requestPost = postRepository.save(Post.builder()
                                .title("foo")
                                .content("bar")
                                .build());
        
        Post requestPost2 = postRepository.save(Post.builder()
                                .title("foo2")
                                .content("bar2")
                                .build());
        
        // expected
        mockMvc.perform(get("/posts")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(requestPost.getId()))
                .andExpect(jsonPath("$[0].title").value("foo"))
                .andExpect(jsonPath("$[0].content").value("bar"))
                .andDo(print());


    }

}