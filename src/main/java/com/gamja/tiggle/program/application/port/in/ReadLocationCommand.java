package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadLocationCommand {

    private Long locationId;

}
