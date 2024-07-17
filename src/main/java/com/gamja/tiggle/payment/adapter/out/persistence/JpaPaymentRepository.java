package com.gamja.tiggle.payment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
