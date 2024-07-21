package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateLocationCommand {
    private Long programId;
    private String locationName;
    private String addressName;
    private String seatImg;
    private String thumbnail;

}
