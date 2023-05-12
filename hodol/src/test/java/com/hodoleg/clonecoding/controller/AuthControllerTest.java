package com.hodoleg.clonecoding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.respository.UserRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class AuthControllerTest {
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    void clean(){
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("회원가입 /auth/sign")
    void test5() throws Exception{
        //given
        var signUser = SignUp.builder()
                .name("LeeJinYoung")
                .password("dkwktm45")
                .email("dkwktm45@gmail.com")
                .build();

        // expected
        mockMvc.perform(post("/auth/sign")
                        .content(objectMapper.writeValueAsString(signUser))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }
}