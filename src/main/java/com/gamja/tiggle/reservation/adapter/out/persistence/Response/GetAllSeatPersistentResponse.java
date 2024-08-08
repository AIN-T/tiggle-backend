package com.gamja.tiggle.reservation.adapter.out.persistence.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
public class GetAllSeatPersistentResponse {

    private Long seatId;
    private String row;
    private int seatNumber;
    private Boolean active;
    private Boolean enable;

    public GetAllSeatPersistentResponse(Long id, String row, int seatNumber, Boolean active, Boolean enable) {
        this.seatId = id;
        this.row = row;
        this.seatNumber = seatNumber;
        this.active = active;
        this.enable = enable;
    }

}
