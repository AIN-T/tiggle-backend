package com.gamja.tiggle.reservation.adapter.in.web.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetRemainedSeatResponse {

    private Long seatId;
    private Long sectionId;
    private int seatNumber;

    public GetRemainedSeatResponse(Long seatId, Long sectionId, int seatNumber) {
        this.seatId = seatId;
        this.sectionId = sectionId;
        this.seatNumber = seatNumber;
    }
}
