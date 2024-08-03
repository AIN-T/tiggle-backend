package com.gamja.tiggle.reservation.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllSeatCommand {

    private Long programId;
    private Long timesId;
    private Long sectionId;
}
