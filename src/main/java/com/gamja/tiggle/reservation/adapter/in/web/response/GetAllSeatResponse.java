package com.gamja.tiggle.reservation.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllSeatResponse {


    private Long seatId;

    private String row;
    private int seatNumber;
    private Boolean active;

}
