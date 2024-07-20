package com.gamja.tiggle.exchange.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadSummaryExchange {
    private String grade;
    private String sectionName;
    private Integer seatNumber;
    private Integer price;
}
