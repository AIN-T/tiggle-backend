package com.gamja.tiggle.exchange.application.port.in;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadExchangeOfferCommand {
    private Long id;
}
