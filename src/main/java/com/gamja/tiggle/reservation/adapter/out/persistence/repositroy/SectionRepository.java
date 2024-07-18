package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    List<SectionEntity> findAllByLocation_Id(Long id);
}
