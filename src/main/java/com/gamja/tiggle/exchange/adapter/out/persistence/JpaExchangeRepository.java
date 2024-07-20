package com.gamja.tiggle.exchange.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExchangeRepository extends JpaRepository<ExchangeEntity,Long> {
        ExchangeEntity findByReservation1IdAndReservation2Id(Long id1,Long id2);
}
