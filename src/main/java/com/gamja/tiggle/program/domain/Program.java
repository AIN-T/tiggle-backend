package com.gamja.tiggle.program.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Program {
    private Long id;
    private Long categoryId;
    private Long locationId;
    private String programName;
    private String programInfo;
    private Location location;

    private LocalDateTime reservationOpenDate;
    private LocalDateTime reservationCloseDate;
    private LocalDateTime currentDateTime;

    private List<String> imageUrls;
    private String locationName;

    private Integer age;
    private Integer runtime;

    private String salerInfo;

    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;

}
