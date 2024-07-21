package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreateProgramCommand {
    private String programName;
    private Long categoryId;
    private Long locationId;
    private String programInfo;
    private LocalDateTime reservationOpenDate;
    private LocalDateTime reservationCloseDate;
    private Integer age;
    private Integer runtime;
    private String salerInfo;
    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;
    private MultipartFile[] imageFiles;

}
