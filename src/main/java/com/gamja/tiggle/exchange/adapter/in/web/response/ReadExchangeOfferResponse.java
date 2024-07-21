package com.gamja.tiggle.exchange.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadExchangeOfferResponse {
    private Long reservationId;
    private String programName;
    private String ticketNumber;
    private String location;
    private ReadSummaryExchange myTicketInfo;
    private ReadSummaryExchange otherTicketInfo;
    private Integer diffPrice;
    private Double point;
}
