package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaProgramRepository extends JpaRepository<ProgramEntity, Long> {
    @Query("SELECT p FROM ProgramEntity p JOIN FETCH p.categoryEntity c WHERE c.id = :categoryId")
    List<ProgramEntity> findAllByCategoryEntity (Long categoryId);

    @Query("SELECT p.locationEntity.id FROM ProgramEntity p WHERE p.id = :programId")
    Long findLocationIdById(Long programId);

}
