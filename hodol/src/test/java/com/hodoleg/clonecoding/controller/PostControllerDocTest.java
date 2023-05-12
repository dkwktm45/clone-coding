package com.hodoleg.clonecoding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.request.PostCreate;
import com.hodoleg.clonecoding.respository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https" ,uriHost = "api.hodolman.com",uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class PostControllerDocTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PostRepository postRepository;


    @Test
    @DisplayName("글 단건 조회 테스트")
    void test() throws Exception {
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(post);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/posts/{postId}",1L)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-inquiry",
                        pathParameters(
                                parameterWithName("postId").description("게시글 ID")
                                ),
                        responseFields(
                                fieldWithPath("id").description("게시글 ID"),
                                fieldWithPath("title").description("게시글 제목"),
                                fieldWithPath("content").description("내용")
                        )
                        ));
    }

    @Test
    @DisplayName("글 등록")
    void test2() throws Exception {
        //given
        PostCreate req = PostCreate.builder()
                .title("제목")
                .content("내용")
                .build();

        String json = objectMapper.writeValueAsString(req);

        this.mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-create",
                        requestFields(
                                fieldWithPath("title").description("제목").attributes(key("constraints").value("좋은 제목 넣어주세요")),
                                fieldWithPath("content").description("내용").optional()
                        )
                ));
    }
}
