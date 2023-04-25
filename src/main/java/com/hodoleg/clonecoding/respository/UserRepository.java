package com.hodoleg.clonecoding.respository;

import com.hodoleg.clonecoding.domain.AuthUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AuthUser,Long> {
    Optional<AuthUser> findByEmailAndPassword(String email, String password);

    Optional<AuthUser> findByEmail(String email);
}
