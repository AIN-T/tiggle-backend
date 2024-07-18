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
    private String myGrade;
    private String mySectionName;
    private Integer mySeatNumber;
    private Integer myPrice;
    private String otherGrade;
    private String otherSectionName;
    private Integer otherSeatNumber;
    private Integer otherPrice;
    private Integer diffPrice;
    private Double point;
}
