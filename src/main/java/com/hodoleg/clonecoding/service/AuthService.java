package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.exception.AlreadyExistsEmailException;
import com.hodoleg.clonecoding.exception.InvalidSigninInformation;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.request.SignUp;
import com.hodoleg.clonecoding.respository.UserRepository;
import com.hodoleg.clonecoding.srypto.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Transactional
    public Long login(Login login){
        AuthUser authUser = userRepository.findByEmail(login.getEmail())
                .orElseThrow(InvalidSigninInformation::new);

        if(!encoder.matches(login.getPassword(), authUser.getPassword())){
            throw new InvalidSigninInformation();
        }

        return authUser.getId();
    }

    public void signin(SignUp signUp) {
        Optional<AuthUser> userOptional = userRepository.findByEmail(signUp.getEmail());
        if(userOptional.isPresent()){
            throw new AlreadyExistsEmailException();
        }

        String encoderPassword = encoder.encrpto(signUp.getPassword());

        var authUser = AuthUser.builder()
                .name(signUp.getName())
                .password(encoderPassword)
                .email(signUp.getEmail())
                .build();
        userRepository.save(authUser);
    }
}
