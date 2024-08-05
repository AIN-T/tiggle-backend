package com.gamja.tiggle.program.adapter.in.web.response;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ReadProgramResponse {
    private Long programId;
    private String programName;
    private String programInfo;
    private LocalDateTime programStartDate;
    private LocalDateTime reservationOpenDate;
    private LocalDateTime programEndDate;
    private List<String> imageFiles;
    private String locationName;
}
