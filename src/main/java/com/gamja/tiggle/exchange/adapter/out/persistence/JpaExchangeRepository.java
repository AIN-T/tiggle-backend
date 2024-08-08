package com.gamja.tiggle.exchange.adapter.out.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaExchangeRepository extends JpaRepository<ExchangeEntity,Long> {
        ExchangeEntity findByReservation1IdAndReservation2Id(Long id1,Long id2);

        @Query("SELECT e FROM ExchangeEntity e "+
                "JOIN FETCH e.reservation1 r " +
                "JOIN FETCH r.programEntity p " +
                "JOIN FETCH p.locationEntity l " +
                "JOIN FETCH r.user u " +
                "JOIN FETCH r.seatEntity s " +
                "JOIN FETCH s.sectionEntity sec " +
                "JOIN FETCH sec.gradeEntity g " +
                "WHERE e.reservation2.user.id = :userId"
        )
        List<ExchangeEntity> findExchangeByUser (Long userId, Pageable page);

        @Query("SELECT COUNT(r) FROM ReservationEntity r " +
                "JOIN r.user u " +
                "WHERE u.id = :userId")
        Long countReservationsByUserId(@Param("userId") Long userId);
}
