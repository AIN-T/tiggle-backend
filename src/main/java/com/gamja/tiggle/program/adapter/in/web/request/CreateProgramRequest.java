package com.gamja.tiggle.program.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProgramRequest {
    private String programName;
    private String programInfo;
    private LocalDateTime reservationOpenDate;
    private LocalDateTime reservationCloseDate;
    private Integer age;
    private Integer runtime;
    private String salerInfo;
    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;
}
