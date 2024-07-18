package com.gamja.tiggle.program.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class GetProgramDetailResponse {

    private String programName;
    private Long locationId;
    private int age;
    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;
    private int runtime;
    private List<String> imageUrls;
    private String programInfo;
}
