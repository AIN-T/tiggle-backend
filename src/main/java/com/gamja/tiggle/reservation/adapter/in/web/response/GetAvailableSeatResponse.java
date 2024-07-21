package com.gamja.tiggle.reservation.adapter.in.web.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAvailableSeatResponse {

    private Long seatId;
    private Long sectionId;
    private int seatNumber;
}
