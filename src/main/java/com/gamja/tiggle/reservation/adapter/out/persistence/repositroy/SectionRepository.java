package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    // TODO locationENtity ID
    List<SectionEntity> findAllByLocationEntityId(Long id);

    Boolean existsByIdAndLocationEntityId(Long sectionId, Long LocationId);
}
