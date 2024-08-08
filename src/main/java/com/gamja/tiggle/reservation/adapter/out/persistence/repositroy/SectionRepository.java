package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSectionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    // TODO locationENtity ID
    @Query("SELECT NEW com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSectionResponse(" +
            "s.id, s.sectionName, s.gradeEntity.id, s.gradeEntity.gradeName, pg.price) " +
            "FROM SectionEntity s " +
            "JOIN s.gradeEntity g " +
            "JOIN ProgramGradeEntity pg ON g.id = pg.gradeEntity.id " +
            "JOIN pg.programEntity p " +
            "WHERE s.locationEntity.id = :locationId " +
            "AND p.id = :programId")
    List<GetSectionResponse> findAllByLocationEntityId(@Param("locationId") Long locationId, @Param("programId") Long ProgramId);

    Boolean existsByIdAndLocationEntityId(Long sectionId, Long LocationId);
}
