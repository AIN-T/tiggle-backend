package com.gamja.tiggle.exchange.adapter.in.web.request;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateExchangeOfferRequest {
   private Long id1;
   private Long id2;
}
