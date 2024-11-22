package com.gamja.tiggle.reservation.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifySeatCommand {

    private Long seatId;
    private Long userId;
    private Long programId;
    private Long timesId;
    private Long sectionId;
    private Integer totalPrice;
}
