package com.gamja.tiggle.program.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaProgramRepository extends JpaRepository<ProgramEntity, Long> {
   @Query("SELECT p FROM ProgramEntity p JOIN FETCH p.categoryEntity c WHERE c.id = :categoryId")
    List<ProgramEntity> findAllByCategoryEntity (Long categoryId);
    List<ProgramEntity> findAllByReservationOpenDateAfter(LocalDateTime currentDateTime);
}
