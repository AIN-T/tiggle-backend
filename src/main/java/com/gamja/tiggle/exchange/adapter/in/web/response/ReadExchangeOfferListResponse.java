package com.gamja.tiggle.exchange.adapter.in.web.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadExchangeOfferListResponse {
    private Long exchangeId;
    private String otherEmail;
    private String programName;
    private String location;
    private String otherGrade;
    private String otherSectionName;
    private Integer otherSeatNumber;
    private Integer otherPrice;
    private Boolean isWatch;
}
