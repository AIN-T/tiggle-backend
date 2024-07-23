package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findBySeatEntityIdAndProgramEntityIdAndTimesEntityId(Long seatId, Long ProgramId, Long timesId);

    Optional<ReservationEntity> findBySeatEntityId(Long seatId);
    List<ReservationEntity> findAllBySeatEntityId(Long seatId);

    @Query("SELECT r FROM ReservationEntity r JOIN FETCH r.exchangeEntity2List e  WHERE e.reservation2.userEntity.id = :userId")
    List<ReservationEntity> findReservationByUser (Long userId);
}
