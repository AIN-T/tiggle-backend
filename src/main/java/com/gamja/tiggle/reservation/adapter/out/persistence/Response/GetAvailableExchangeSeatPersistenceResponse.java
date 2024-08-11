package com.gamja.tiggle.reservation.adapter.out.persistence.Response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAvailableExchangeSeatPersistenceResponse {

    private Long seatId;
    private String row;
    private int seatNumber;
    private Boolean active;
    private Boolean enable;
    private Long reservationId;
    private Integer price;

    public GetAvailableExchangeSeatPersistenceResponse(Long id, String row, int seatNumber, Boolean active, Boolean enable, Long reservationId, Integer totalPrice) {
        this.seatId = id;
        this.row = row;
        this.seatNumber = seatNumber;
        this.active = active;
        this.enable = enable;
        this.reservationId = reservationId;
        this.price = totalPrice;
    }
}
