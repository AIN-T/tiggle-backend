package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAllSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAvailableSeatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {


//   모든 좌석 조회
    Optional<List<SeatEntity>> findAllBySectionEntityId(Long sectionId);

//    모든 좌석 조회 Query 사용
    @Query("SELECT s FROM SeatEntity s WHERE s.sectionEntity.id = :sectionId ORDER BY s.row, s.seatNumber")
    List<SeatEntity> findAllBySectionEntityIdByQuery(@Param("sectionId") Long sectionId);

//    모든 좌석 조회 (예약 가능 여부까지)
    @Query("SELECT new com.gamja.tiggle.reservation.adapter.out.persistence.Response" +
            ".GetAllSeatResponse(s.id, s.seatNumber, s.row, s.active, coalesce(r.available, true)) " +
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
            "WHERE s.sectionEntity.id = :sectionId ")
    List<GetAllSeatResponse> findAllSeat(@Param("programId") Long programId,
                                         @Param("timesId") Long timesId,
                                         @Param("sectionId") Long sectionId);

    //예약 가능 좌석만 조회
    @Query("SELECT new com.gamja.tiggle.reservation.adapter.out.persistence.Response" +
            ".GetAvailableSeatResponse(s.id, s.sectionEntity.id, s.seatNumber, s.row) " +
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
    List<GetAvailableSeatResponse> findAvailableSeat(@Param("programId") Long programId,
                                                     @Param("timesId") Long timesId,
                                                     @Param("sectionId") Long sectionId);



}
