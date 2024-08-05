package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadReservationCommand {
    private User user;
    private Long reservationId;
}
