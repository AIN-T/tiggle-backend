package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.domain.Payment;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentPersistencePort {
    private final JpaPaymentRepository jpaPaymentRepository;
    private final ReservationRepository jpaReservationRepository;

    @Override
    public void savePayment(Payment payment) throws BaseException {
        Optional<ReservationEntity> result = jpaReservationRepository.findById(payment.getReservationId());
        //if (!(result.getState)) {
        PaymentEntity entity = PaymentEntity.builder()
                .username(payment.getUsername())
                .ticketPrice(payment.getTicketPrice())
                .usePoint(payment.getUsePoint())
                .fee(payment.getFee())
                .payType(payment.getPayType())
                .verify(payment.getVerify())
                .reservationEntity(result.get())
                .build();

        entity.createdAt();
        jpaPaymentRepository.save(entity);
        //}
//        else{
//            throw BaseException(BaseResponseStatus.이미 결제된 티켓);
//        }
    }

    @Override
    public Payment searchPayment(Long id) throws BaseException {
        PaymentEntity result = jpaPaymentRepository.findByReservationEntity_Id(id);
        if (result != null) {
            return Payment.builder()
                    .reservationId(result.getReservationEntity().getId())
                    .username(result.getUsername())
                    .ticketPrice(result.getTicketPrice())
                    .usePoint(result.getUsePoint())
                    .fee(result.getFee())
                    .payType(result.getPayType())
                    .verify(result.getVerify())
                    .createdAt(result.getCreatedAt())
                    .verifiedAt(result.getVerifiedAt())
                    .build();
        } else {
            //throw BaseException(BaseResponseStatus.잘못된 티켓);
        }
        return null;
    }

    @Override
    public void verify(Payment payment) throws BaseException {
        PaymentEntity searchedEntity = jpaPaymentRepository.findByReservationEntity_Id(payment.getReservationId());
        PaymentEntity entity = PaymentEntity.builder()
                .id(searchedEntity.getId())
                .reservationEntity(searchedEntity.getReservationEntity())
                .username(searchedEntity.getUsername())
                .ticketPrice(searchedEntity.getTicketPrice())
                .usePoint(searchedEntity.getUsePoint())
                .fee(searchedEntity.getFee())
                .payType(searchedEntity.getPayType())
                .verify(true)
                .build();

        entity.verifiedAt();
        jpaPaymentRepository.save(entity);
    }
}

