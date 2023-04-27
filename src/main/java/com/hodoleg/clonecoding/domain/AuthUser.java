package com.hodoleg.clonecoding.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    @Builder
    public AuthUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "authUser")
    private List<Session> sessions = new ArrayList<>();

    public Session addSession(){
        Session session = Session.builder().authUser(this).build();
        sessions.add(session);
        return session;
    }
}
