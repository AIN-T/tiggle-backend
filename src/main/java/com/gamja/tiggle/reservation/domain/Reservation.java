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

    private Program program;
//    private User user;
    private Seat seat;
    private Times times;

    private String ticketNumber;
    private String payMethod;
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationType status;

    private Integer requestLimit;
    private Integer refund;

}
