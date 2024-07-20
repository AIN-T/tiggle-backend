package com.gamja.tiggle.exchange.adapter.in.web.request;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateExchangeApprovalRequest {
    private Long exchangeId;
    private Boolean isAgree;
}
