package com.gamja.tiggle.reservation.adapter.out.persistence.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllSeatResponse {

    private Long seatId;
    private int seatNumber;
    private String row;
    private Boolean active;
    private Boolean enable;

    public GetAllSeatResponse(Long id, int seatNumber, String row, Boolean active, Boolean enable) {
        this.seatId = id;
        this.seatNumber = seatNumber;
        this.row = row;
        this.active = active;
        this.enable = enable;
    }
}
