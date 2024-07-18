package com.gamja.tiggle.times.adapter.out.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesRepository extends JpaRepository<TimesEntity, Long> {

    List<TimesEntity> findAllByProgramId(Long id);
}
