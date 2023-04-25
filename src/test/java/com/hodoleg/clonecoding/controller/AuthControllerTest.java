package com.hodoleg.clonecoding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.domain.Session;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.respository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
    @DisplayName("로그인 생성 후 세션 응답")
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
    @Test
    @DisplayName("로그인 후 권한이 필요한 페이지 접속한다. /foo")
    void test3() throws Exception{
        //given
        AuthUser authUser = AuthUser.builder()
                .name("호돌맨")
                .email("hodoman88@gmail.com")
                .password("1234").build();

        userRepository.save(authUser);

        String json = objectMapper.writeValueAsString(Login.builder()
                .email(authUser.getEmail())
                .password(authUser.getPassword()).build());

        MvcResult mvcResult = mockMvc.perform(post("/auth/login")
                        .content(json)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONParser jsonParser = new JSONParser();
        JSONObject authorization = (JSONObject) jsonParser.parse(mvcResult.getResponse().getContentAsString());
        // expected
        mockMvc.perform(get("/foo")
                        .header("Authorization",authorization.get("accessToken"))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지 접속할 수 없다. /foo")
    void test4() throws Exception{
        //given
        AuthUser authUser = AuthUser.builder()
                .name("호돌맨")
                .email("hodoman88@gmail.com")
                .password("1234").build();
        Session session = authUser.addSession();

        userRepository.save(authUser);

        // expected
        mockMvc.perform(get("/foo")
                        .header("Authorization","ewflwennn31r3n1pmdwdw")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());

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