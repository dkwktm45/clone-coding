package com.hodoleg.clonecoding.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessToken;
    @ManyToOne
    private AuthUser authUser;

    @Builder
    public Session(String accessToken, AuthUser authUser) {
        this.accessToken = UUID.randomUUID().toString();
        this.authUser = authUser;
    }
}