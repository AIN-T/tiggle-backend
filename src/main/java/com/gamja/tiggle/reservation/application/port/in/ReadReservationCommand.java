package com.gamja.tiggle.reservation.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadReservationCommand {
    private Long reservationId;
}
