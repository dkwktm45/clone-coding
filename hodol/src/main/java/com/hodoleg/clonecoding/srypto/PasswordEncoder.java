package com.hodoleg.clonecoding.srypto;

public interface PasswordEncoder {
    String encrypt(String rawPassword);
    boolean matches(String rawPassword , String encryptedPassword);
}
