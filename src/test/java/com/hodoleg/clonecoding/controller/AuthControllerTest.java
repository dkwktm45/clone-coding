package com.hodoleg.clonecoding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.respository.SessionRepository;
import com.hodoleg.clonecoding.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AuthControllerTest {
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    void clean(){
        userRepository.deleteAll();
    }
    @Test
    @DisplayName("로그인 생성")
    void test() throws Exception{
        //given
        userRepository.save(AuthUser.builder()
                .name("호돌맨")
                .email("hodoman88@gmail.com")
                .password("1234").build());
        // 암호화 알고리짐 --> scrypt , bcrypt

        Login login = Login.builder()
                .email("hodoman88@gmail.com")
                .password("1234")
                .build();


        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("로그인 생성 후 세션객체 응답")
    void test2() throws Exception{
        //given
        userRepository.save(AuthUser.builder()
                .name("호돌맨")
                .email("hodoman88@gmail.com")
                .password("1234").build());

        Login login = Login.builder()
                .email("hodoman88@gmail.com")
                .password("1234")
                .build();


        String json = objectMapper.writeValueAsString(login);

        // expected
        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andDo(print());

    }

}