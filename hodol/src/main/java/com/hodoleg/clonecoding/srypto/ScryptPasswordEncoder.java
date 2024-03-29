package com.hodoleg.clonecoding.srypto;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class ScryptPasswordEncoder implements PasswordEncoder{
    private static final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16
            ,8
            ,1
            ,32
            ,64);
    @Override
    public String encrypt(String rawPasword){
        return encoder.encode(rawPasword);
    }

    @Override
    public boolean matches(String rawPassword, String encryptedPassword){
        return encoder.matches(rawPassword,encryptedPassword);
    }
}
