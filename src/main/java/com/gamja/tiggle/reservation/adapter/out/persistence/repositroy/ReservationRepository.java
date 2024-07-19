package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    Optional<ReservationEntity> findBySeatEntityId(Long seatId);

    @Query("SELECT r FROM ReservationEntity r JOIN FETCH r.exchangeEntity2List e  WHERE e.reservation2.user.id = :userId")
    List<ReservationEntity> findReservationByUser (Long userId);
}
