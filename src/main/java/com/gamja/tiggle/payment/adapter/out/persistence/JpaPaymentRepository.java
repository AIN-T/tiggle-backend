package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.reservation.adapter.out.persistence.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
    PaymentEntity findByReservationEntity_Id(Long id);
}
