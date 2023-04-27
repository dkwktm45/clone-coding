package com.hodoleg.clonecoding.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
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