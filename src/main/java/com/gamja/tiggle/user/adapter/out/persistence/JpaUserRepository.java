package com.gamja.tiggle.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    Boolean existsByEmail(String email);
}
