package com.gamja.tiggle.reservation.domain;

import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Reservation {

    private Long id;

    private Long programId;
    private User user;
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
