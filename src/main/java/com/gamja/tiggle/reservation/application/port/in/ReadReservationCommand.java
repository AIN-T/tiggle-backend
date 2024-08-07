package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadReservationCommand {
    private User user;
    private Long reservationId;

    // 페이징 처리 하려고
    private Integer page;
    private Integer size;
}
