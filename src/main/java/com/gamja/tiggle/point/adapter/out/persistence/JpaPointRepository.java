package com.gamja.tiggle.point.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPointRepository extends JpaRepository<PointEntity, Long> {
    List<PointEntity> findAllByUserId(Long userId);
}
