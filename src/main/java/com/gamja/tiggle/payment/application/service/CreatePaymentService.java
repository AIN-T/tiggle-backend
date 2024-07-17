package com.gamja.tiggle.payment.application.service;

import com.gamja.tiggle.common.BaseException;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.payment.application.port.in.CreatePaymentCommand;
import com.gamja.tiggle.payment.application.port.in.CreatePaymentUseCase;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePaymentService implements CreatePaymentUseCase {
    private final PaymentPersistencePort paymentPersistencePort;

    @Override
    public void save(CreatePaymentCommand command) throws BaseException {
        Payment payment = Payment.builder()
                .reservationId(command.getReservationId())
                .username(command.getUsername())
                .ticketPrice(command.getTicketPrice())
                .usePoint(command.getUsePoint())
                .fee(command.getFee())
                .string(command.getString())
                .verify(false)
                .build();
        paymentPersistencePort.savePayment(payment);
    }
}
