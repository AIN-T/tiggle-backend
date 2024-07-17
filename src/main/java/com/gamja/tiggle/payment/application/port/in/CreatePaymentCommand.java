package com.gamja.tiggle.payment.application.port.in;

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
    private String string;
    private Boolean verify;
}

