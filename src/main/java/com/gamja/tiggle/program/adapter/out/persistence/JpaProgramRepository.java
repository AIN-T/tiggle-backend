package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaProgramRepository extends JpaRepository<ProgramEntity, Long> {
    @Query("SELECT p FROM ProgramEntity p JOIN FETCH p.categoryEntity c WHERE c.id = :categoryId")
    Page<ProgramEntity> findAllByCategoryEntity (Long categoryId, Pageable pageable);

    @Query("SELECT p FROM ProgramEntity p WHERE p.programName LIKE %:keyword% OR p.programInfo LIKE %:keyword%")
    Page<ProgramEntity> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p.locationEntity.id FROM ProgramEntity p WHERE p.id = :programId")
    Long findLocationIdById(Long programId);

    Boolean existsProgramEntityByIdAndLocationEntityId(Long programId, Long locationId);

}
