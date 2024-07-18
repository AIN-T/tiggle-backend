package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProgramImageRepository extends JpaRepository<ProgramImageEntity, Long> {

}
