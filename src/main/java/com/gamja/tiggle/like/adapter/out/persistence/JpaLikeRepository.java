package com.gamja.tiggle.like.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, Long> {
}
