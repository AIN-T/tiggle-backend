package com.gamja.tiggle.exchange.application.port.in;


import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadExchangeOfferCommand {
    private Long id;
    private User user;
}
