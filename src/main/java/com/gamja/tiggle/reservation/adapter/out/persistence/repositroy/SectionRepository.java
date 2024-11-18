package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSectionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    @Query("SELECT NEW com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSectionResponse(" +
            "s.id, s.sectionName, s.gradeEntity.id, s.gradeEntity.gradeName, pg.price, " +
            "(SELECT COUNT(seat.id) " +
            " FROM SeatEntity seat " +
            " LEFT JOIN ReservationEntity re ON seat.id = re.seatEntity.id AND re.programEntity.id = :programId " +
            " WHERE seat.sectionEntity.id = s.id AND seat.active = TRUE " +
            " AND (re.id IS NULL OR re.available = TRUE))) " + // AS 제거
            "FROM SectionEntity s " +
            "JOIN s.gradeEntity g " +
            "JOIN ProgramGradeEntity pg ON g.id = pg.gradeEntity.id " +
            "JOIN pg.programEntity p " +
            "WHERE s.locationEntity.id = :locationId " +
            "AND p.id = :programId")
    List<GetSectionResponse> findAllByLocationEntityIdWithRemainingCount(
            @Param("locationId") Long locationId,
            @Param("programId") Long programId
    );
    Boolean existsByIdAndLocationEntityId(Long sectionId, Long LocationId);

    @Query("SELECT s FROM SectionEntity s WHERE s.locationEntity.id = :locationId")
    Page<SectionEntity> findFirstSectionByProgramIdAndLocationId(@Param("locationId") Long locationId, Pageable pageable);

}
