package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ReadTemporaryReservationCommand {
    private User user;
    private String ticketNumber;
}
