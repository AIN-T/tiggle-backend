package com.gamja.tiggle.payment.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyPaymentCommand {
    private String paymentId;
    private Long reservationId;
}

