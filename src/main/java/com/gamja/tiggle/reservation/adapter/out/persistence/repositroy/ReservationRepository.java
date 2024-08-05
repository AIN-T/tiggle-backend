package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import com.gamja.tiggle.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findBySeatEntityIdAndProgramEntityIdAndTimesEntityId(Long seatId, Long ProgramId, Long timesId);
    Optional<ReservationEntity> findBySeatEntityId(Long seatId);

    @Query("SELECT r FROM ReservationEntity r " +
            "JOIN FETCH r.seatEntity s " +
            "JOIN FETCH r.programEntity p " +
            "JOIN FETCH r.user u " +
            "WHERE r.id = :reservationId")
    ReservationEntity findReservationWithDetails(@Param("reservationId") Long reservationId);

    @Query("SELECT r FROM ReservationEntity r " +
            "JOIN FETCH r.seatEntity s " +
            "JOIN FETCH r.programEntity p " +
            "JOIN FETCH r.user u " +
            "WHERE u.id = :userId")
    List<ReservationEntity> findReservationsByUserId(@Param("userId") Long userId);
}
