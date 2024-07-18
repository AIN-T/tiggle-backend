package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentPersistencePort {
    private final JpaPaymentRepository jpaPaymentRepository;
    //private final JpaReservationRepository jpaReservationRepository;

    @Override
    public void savePayment(Payment payment) throws BaseException {
        //ReservationEntity result = jpaReservationRepository.findById(payment.getReservationId())
        //if (!(result.getState)) {
        PaymentEntity entity = PaymentEntity.builder()
                .username(payment.getUsername())
                .ticketPrice(payment.getTicketPrice())
                .usePoint(payment.getUsePoint())
                .fee(payment.getFee())
                .string(payment.getString())
                .verify(payment.getVerify())
                //.reservation(result)
                .build();

        entity.createdAt();
        jpaPaymentRepository.save(entity);
        //}
//        else{
//            throw BaseException(BaseResponseStatus.이미 결제된 티켓);
//        }
    }

    @Override
    public Payment searchPayment(Payment payment) throws BaseException {
        PaymentEntity result = jpaPaymentRepository.findByReservationId(payment.getReservationId());
        if (result != null) {
            return Payment.builder()
                    .username(result.getUsername())
                    .ticketPrice(result.getTicketPrice())
                    .usePoint(result.getUsePoint())
                    .fee(result.getFee())
                    .string(result.getString())
                    .verify(result.getVerify())
                    .createdAt(result.getCreatedAt())
                    .verifiedAt(result.getVerifiedAt())
                    .build();
        }
        else {
            //throw BaseException(BaseResponseStatus.잘못된 티켓);
        }
        return null;
    }

    @Override
    public void verify(Payment payment) throws BaseException {
        PaymentEntity searchedEntity = jpaPaymentRepository.findByReservationId(payment.getReservationId());
        PaymentEntity entity = PaymentEntity.builder()
                .id(searchedEntity.getId())
                .verify(true)
                .build();

        entity.verifiedAt();
        jpaPaymentRepository.save(entity);
    }
}

