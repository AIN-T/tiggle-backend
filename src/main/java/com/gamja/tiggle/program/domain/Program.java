package com.gamja.tiggle.program.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Program {
    private Long id;
    private String programName;
    private String programInfo;

    private LocalDateTime reservationOpenDate;
    private LocalDateTime reservationCloseDate;

    private Long categoryIdx;
    private List<String> imageUrls;

    private Integer age;
    private Integer runtime;

    private String salerInfo;

    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;

}
