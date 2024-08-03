package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSeatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {


    Optional<List<SeatEntity>> findAllBySectionEntityId(Long sectionId);

    @Query("SELECT s FROM SeatEntity s WHERE s.sectionEntity.id = :sectionId ORDER BY s.row, s.seatNumber")
    List<SeatEntity> findAllBySectionEntityIdByQuery(@Param("sectionId") Long sectionId);

    //예약 가능 좌석만 조회
    @Query("SELECT new com.gamja.tiggle.reservation.adapter.out.persistence.Response" +
            ".GetSeatResponse(s.id, s.sectionEntity.id, s.seatNumber, s.row) " +
            "FROM SeatEntity s " +
            "LEFT JOIN ReservationEntity r ON s.id = r.seatEntity.id " +
            "AND r.programEntity.id = :programId " +
            "AND r.timesEntity.id = :timesId " +
            "AND COALESCE(r.modifiedAt, r.createdAt) = ( " +
            "  SELECT MAX(COALESCE(r2.modifiedAt, r2.createdAt)) " +
            "  FROM ReservationEntity r2 " +
            "  WHERE r2.seatEntity.id = r.seatEntity.id " +
            "  AND r2.programEntity.id = :programId " +
            "  AND r2.timesEntity.id = :timesId " +
            ") " +
            "WHERE s.sectionEntity.id = :sectionId " +
            "AND COALESCE(r.available, TRUE) = TRUE")
    List<GetSeatResponse> findAvailableSeat(@Param("programId") Long programId,
                                            @Param("timesId") Long timesId,
                                            @Param("sectionId") Long sectionId);


}
