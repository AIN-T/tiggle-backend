package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.in.web.response.GetAvailableExchangeSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAllSeatPersistentResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAvailableExchangeSeatPersistenceResponse;
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
    @Query("SELECT NEW com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAllSeatPersistentResponse(" +
            "s.id, s.row, s.seatNumber, s.active, " +
            "CASE WHEN r.id IS NULL THEN true ELSE r.available END) " +
            "FROM SeatEntity s " +
            "LEFT JOIN ReservationEntity r ON s.id = r.seatEntity.id " +
            "AND r.programEntity.id = :programId " +
            "AND r.timesEntity.id = :timesId " +
            "AND COALESCE(r.modifiedAt, r.createdAt) = ( " +
            "  SELECT MAX(COALESCE(r2.modifiedAt, r2.createdAt)) " +
            "  FROM ReservationEntity r2 " +
            "  WHERE r2.seatEntity.id = r.seatEntity.id " +
            "  AND r2.programEntity.id = :programId " +
            "  AND r2.timesEntity.id = :timesId) " +
            "WHERE s.sectionEntity.id = :sectionId " +
            "ORDER BY s.row, s.seatNumber")
    List<GetAllSeatPersistentResponse> findAllSeat(@Param("programId") Long programId,
                                                   @Param("timesId") Long timesId,
                                                   @Param("sectionId") Long sectionId);


    @Query("SELECT NEW com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAllSeatPersistentResponse(" +
            "s.id, s.row, s.seatNumber, s.active, " +
            "CASE " +
            "WHEN r.status = 'COMPLETED' OR r.status = 'EXCHANGED' THEN TRUE ELSE FALSE " +
            "END)" +
            "FROM SeatEntity s " +
            "LEFT JOIN ReservationEntity r ON s.id = r.seatEntity.id " +
            "AND r.programEntity.id = :programId " +
            "AND r.timesEntity.id = :timesId " +
            "AND COALESCE(r.modifiedAt, r.createdAt) = ( " +
            "  SELECT MAX(COALESCE(r2.modifiedAt, r2.createdAt)) " +
            "  FROM ReservationEntity r2 " +
            "  WHERE r2.seatEntity.id = r.seatEntity.id " +
            "  AND r2.programEntity.id = :programId " +
            "  AND r2.timesEntity.id = :timesId) " +
            "WHERE s.sectionEntity.id = :sectionId " +
            "ORDER BY s.row, s.seatNumber")
    List<GetAllSeatPersistentResponse> findAllSeatEnableExchange(@Param("programId") Long programId,
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



//    교환 가능 좌석 조회
@Query("SELECT NEW com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAvailableExchangeSeatPersistenceResponse(" +
        "s.id, s.row, s.seatNumber, s.active, " +
        "CASE " +
        "     WHEN r IS NOT NULL AND r.user.id = :userId THEN FALSE " +
        "     WHEN r IS NOT NULL AND r.status = 'COMPLETED' THEN TRUE " +
        "     ELSE FALSE END, " +
        "CASE WHEN r IS NOT NULL THEN r.id ELSE NULL END, " +  // r가 null일 때 자동으로 null 반환
        "CASE WHEN r IS NOT NULL THEN r.totalPrice ELSE NULL END) " +  // r가 null일 때 자동으로 null 반환
        "FROM SeatEntity s " +
        "LEFT JOIN ReservationEntity r ON s.id = r.seatEntity.id " +
        "AND r.programEntity.id = :programId " +
        "AND r.timesEntity.id = :timesId " +
        "AND COALESCE(r.modifiedAt, r.createdAt) = ( " +
        "  SELECT MAX(COALESCE(r2.modifiedAt, r2.createdAt)) " +
        "  FROM ReservationEntity r2 " +
        "  WHERE r2.seatEntity.id = r.seatEntity.id " +
        "  AND r2.programEntity.id = :programId " +
        "  AND r2.timesEntity.id = :timesId) " +
        "WHERE s.sectionEntity.id = :sectionId " +
        "ORDER BY s.row, s.seatNumber")
List<GetAvailableExchangeSeatPersistenceResponse> findAvailableExchange(Long programId, Long sectionId, Long timesId, Long userId);

}
