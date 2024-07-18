package com.gamja.tiggle.program.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProgramGrade {

    private Long id;
    private Long programId;
    private Long gradeId;
    private int price;
    private String gradeName;

}
