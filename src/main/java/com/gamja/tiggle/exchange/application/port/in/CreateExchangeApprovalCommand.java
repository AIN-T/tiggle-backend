package com.gamja.tiggle.exchange.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateExchangeApprovalCommand {
    private Long exchangeId;
    private Long reservationId1;
    private Long reservationId2;
    private Boolean isAgree;
}
