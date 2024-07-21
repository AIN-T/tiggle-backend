package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadProgramCommand {

    private Long categoryId;
    private Long locationId;
    private LocalDateTime currentDateTime;

    // 페이징 처리 하려고
    private Integer page;
    private Integer size;

}
