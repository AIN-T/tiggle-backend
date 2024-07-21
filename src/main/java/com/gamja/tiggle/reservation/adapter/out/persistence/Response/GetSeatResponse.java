package com.gamja.tiggle.reservation.adapter.out.persistence.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetSeatResponse {

    private Long seatId;
    private Long sectionId;
    private int seatNumber;


    public GetSeatResponse(Long seatId, Long sectionId, int seatNumber) {
        this.seatId = seatId;
        this.sectionId = sectionId;
        this.seatNumber = seatNumber;

    }
}
