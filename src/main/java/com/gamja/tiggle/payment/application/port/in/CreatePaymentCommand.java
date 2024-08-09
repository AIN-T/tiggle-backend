package com.gamja.tiggle.payment.application.port.in;

import com.gamja.tiggle.payment.domain.PayType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePaymentCommand {
    private Long reservationId;
    private String username;
    private Integer ticketPrice;
    private Integer usePoint;
    private Integer fee;
    private PayType payType;
    private Boolean verify;
}

