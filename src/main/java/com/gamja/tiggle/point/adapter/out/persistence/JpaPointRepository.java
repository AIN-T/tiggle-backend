package com.gamja.tiggle.point.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPointRepository extends JpaRepository<PointEntity, Long> {
}
