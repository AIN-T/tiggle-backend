package com.gamja.tiggle.reservation.domain;

import com.gamja.tiggle.program.domain.Program;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Reservation {

    private Long reservationId;

    private Long programId;
//    private User user;
    private Long seatId;
    private Long timesId;

    private String ticketNumber;
    private String payMethod;
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationType status;

    private Integer requestLimit;
    private Integer refund;

}
