package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}

