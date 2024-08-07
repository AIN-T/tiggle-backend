package com.gamja.tiggle.reservation.adapter.out.persistence.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAvailableSeatResponse {

    private Long seatId;
    private Long sectionId;
    private int seatNumber;
    private String row;

    public GetAvailableSeatResponse(Long seatId, Long sectionId, int seatNumber, String row) {
        this.seatId = seatId;
        this.sectionId = sectionId;
        this.seatNumber = seatNumber;
        this.row = row;
    }
}
