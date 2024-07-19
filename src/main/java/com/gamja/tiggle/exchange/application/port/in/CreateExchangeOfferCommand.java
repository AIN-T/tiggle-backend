package com.gamja.tiggle.exchange.application.port.in;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateExchangeOfferCommand {
    private Long reservationId1;
    private Long reservationId2;
}
