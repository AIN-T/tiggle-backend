package com.gamja.tiggle.exchange.application.port.in;


import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadExchangeOfferListCommand {
    private User user;
    private Integer page;
    private Integer size;
}
