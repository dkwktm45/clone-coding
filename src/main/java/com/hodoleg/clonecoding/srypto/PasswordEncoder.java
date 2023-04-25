package com.hodoleg.clonecoding.srypto;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    private static final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16
            ,8
            ,1
            ,32
            ,64);

    public String encrpto(String rawPasword){
        return encoder.encode(rawPasword);
    }
    public boolean matches(String rawPassword, String encryptedPassword){
        return encoder.matches(rawPassword,encryptedPassword);
    }
}
