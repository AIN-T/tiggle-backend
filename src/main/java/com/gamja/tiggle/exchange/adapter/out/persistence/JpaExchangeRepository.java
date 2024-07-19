package com.gamja.tiggle.exchange.adapter.out.persistence;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExchangeRepository extends JpaRepository<ExchangeEntity,Long> {
        ExchangeEntity findByReservation1IdAndReservation2Id(Long id1,Long id2);
}
