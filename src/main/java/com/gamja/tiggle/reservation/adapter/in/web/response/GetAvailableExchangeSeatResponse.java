package com.gamja.tiggle.reservation.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAvailableExchangeSeatResponse {

    private Long seatId;

    private String row;
    private int seatNumber;
    private Boolean active;
    private Boolean enable;
    private Long reservationId;
    private Integer price;
}
