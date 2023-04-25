package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.exception.AlreadyExistsEmailException;
import com.hodoleg.clonecoding.exception.InvalidSigninInformation;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public Long signin(Login login){
        AuthUser authUser = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        return authUser.getId();
    }

    public void signin(SignUp signUp) {
        Optional<AuthUser> userOptional = userRepository.findByEmail(signUp.getEmail());
        if(userOptional.isPresent()){
            throw new AlreadyExistsEmailException();
        }
        var authUser = AuthUser.builder()
                .name(signUp.getName())
                .password(signUp.getPassword())
                .email(signUp.getEmail())
                .build();
        userRepository.save(authUser);
    }
}
