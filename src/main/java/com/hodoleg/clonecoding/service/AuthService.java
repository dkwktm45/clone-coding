package com.hodoleg.clonecoding.service;

import com.hodoleg.clonecoding.domain.AuthUser;
import com.hodoleg.clonecoding.exception.InvalidSigninInformation;
import com.hodoleg.clonecoding.request.Login;
import com.hodoleg.clonecoding.response.SessionResponse;
import com.hodoleg.clonecoding.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public SessionResponse signin(Login login){
        AuthUser authUser = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        return new SessionResponse(authUser.addSession().getAccessToken());
    }
}
