package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.exception.AlreadyExistsEmailException;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.respository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        //given
        var authUser = SignUp.builder()
                .name("LeeJinYoung")
                .password("dkwktm45")
                .email("dkwktm45@gmail.com")
                .build();
        //when
        authService.signin(authUser);
        //then
        assertEquals(1L , userRepository.count());
        AuthUser result = userRepository.findAll().iterator().next();
        assertEquals("LeeJinYoung",result.getName());
        assertEquals("dkwktm45@gmail.com",result.getEmail());
    }
    @Test
    @DisplayName("회원가입시 이미 가입된 이메일로 AlreadyExistsEmailException 에러 발생")
    void test2() {
        //given
        var authUser = SignUp.builder()
                .name("LeeJinYoung")
                .password("dkwktm45")
                .email("dkwktm45@gmail.com")
                .build();
        userRepository.save(AuthUser.builder()
                .name("LeeJinYoung")
                .email("dkwktm45@gmail.com")
                .password("dkwktm45").build());
        //when
        AlreadyExistsEmailException error = Assertions.assertThrows(AlreadyExistsEmailException.class
                ,() -> {authService.signin(authUser);});
        //then
        assertEquals("이미 가입된 이메일입니다." , error.getMessage());
    }

}