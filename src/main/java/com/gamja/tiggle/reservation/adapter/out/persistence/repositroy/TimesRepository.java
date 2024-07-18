package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;


import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.TimesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesRepository extends JpaRepository<TimesEntity, Long> {

    List<TimesEntity> findAllByProgramEntityId(Long id);
}
