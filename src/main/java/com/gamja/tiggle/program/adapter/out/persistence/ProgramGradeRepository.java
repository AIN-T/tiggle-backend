package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramGradeRepository extends JpaRepository<ProgramGradeEntity, Long> {

    List<ProgramGradeEntity> findAllByProgramEntityId(Long id);
}
