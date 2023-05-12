package com.hodoleg.clonecoding.request;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SignUp {
    private String email;
    private String name;
    private String password;

}
