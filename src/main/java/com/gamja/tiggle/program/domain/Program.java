package com.gamja.tiggle.program.domain;

import lombok.Builder;
import lombok.Getter;
import org.example.tiggle.common.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Program extends BaseEntity {
    private Long id;
    private String programName;
    private String programInfo;

    private LocalDateTime reservationOpenDate;
    private LocalDateTime reservationCloseDate;

    private List<String> imageUrls;

    private Integer age;
    private Integer runtime;

    private String salerInfo;

    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;

    // Location - name 받아오기 ()
    // Category - categoryName 받아오기 (1 : N)

}
