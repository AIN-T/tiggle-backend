package com.gamja.tiggle.exchange.application.port.in;

import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateExchangeApprovalCommand {
    private User user;
    private Long exchangeId;
    private Long reservationId1;
    private Long reservationId2;
    private Boolean isAgree;
}
