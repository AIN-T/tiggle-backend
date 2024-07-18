package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadProgramCommand {

    private Long categoryId;
    private LocalDateTime currentDateTime;

}
